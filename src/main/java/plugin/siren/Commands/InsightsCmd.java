package plugin.siren.Commands;

import com.hypixel.hytale.assetstore.AssetPack;
import com.hypixel.hytale.common.plugin.PluginManifest;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.asset.AssetModule;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import plugin.siren.HytaleInsights;
import plugin.siren.Utils.API.DiscordWebhook;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class InsightsCmd extends AbstractAsyncCommand {
    public InsightsCmd() {
        super("insights", "server.commands.hytaleinsights.insights.desc");

        this.setPermissionGroup(GameMode.Adventure);
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {
        /*if (!context.isPlayer()) {
            return CompletableFuture.completedFuture(null);
        } else {
            Ref<EntityStore> ref = context.senderAsPlayerRef();
            if (ref != null && ref.isValid()) {
                Store<EntityStore> store = ref.getStore();
                World world = store.getExternalData().getWorld();
                return CompletableFuture.runAsync(() -> {
                    Player player = store.getComponent(ref, Player.getComponentType());
                    PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
                    if (player != null && playerRef != null) {

                    }
                }, world);
            } else {
                return CompletableFuture.completedFuture(null);
            }
        }*/

        boolean ignoreHytale = true;

        List<PluginManifest> pluginsList = new ArrayList<>(PluginManager.get().getAvailablePlugins().values());

        List<AssetPack> assetsPacksList = new ArrayList<>(AssetModule.get().getAssetPacks());
        for(AssetPack assetPack : assetsPacksList){
            PluginManifest assetPlugin = assetPack.getManifest();

            boolean addPlugin = true;
            if(!pluginsList.isEmpty()) {
                for (PluginManifest plugin : pluginsList) {
                    if (plugin.getName().equals(assetPlugin.getName()) && plugin.getGroup().equals(assetPlugin.getGroup())) {
                        addPlugin = false;
                    }
                }
            }

            if(addPlugin) {
                pluginsList.add(assetPlugin);
            }
        }

        if(!pluginsList.isEmpty()) {
            String discordWebhookMessage = "";

            for (PluginManifest plugin : pluginsList) {
                if (ignoreHytale && plugin.getGroup().equals("Hytale")) {
                    continue;
                }

                String message = plugin.getGroup() + ":" + plugin.getName() + " v" + plugin.getVersion();

                HytaleInsights.LOGGER.atInfo().log(message);
                discordWebhookMessage += message + "\n";
            }

            if(HytaleInsights.getConfig().get().ifDiscord()) {
                DiscordWebhook discordWebhook = new DiscordWebhook(HytaleInsights.getConfig().get().getDiscordWebhook());
                if(!discordWebhook.getUrl().equalsIgnoreCase(HytaleInsights.getConfig().get().getDiscordWebhookDefault())) {
                    discordWebhook.setContent(discordWebhookMessage);
                    try {
                        discordWebhook.execute();
                    } catch (IOException e) {
                        HytaleInsights.LOGGER.atWarning().log("Exception with discordWebhook.execute(), possible invalid discord webhook url : " + e.toString());
                    }
                }
            }
        }else{
            HytaleInsights.LOGGER.atWarning().log("You currently have no mods installed on the server. ERROR pluginsList is empty");
        }

        return CompletableFuture.completedFuture(null);
    }
}