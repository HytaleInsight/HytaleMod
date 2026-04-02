package plugin.siren.Commands.Insights;

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

public class StatusCmd extends AbstractAsyncCommand {
    public StatusCmd() {
        super("status", "server.commands.hytaleinsights.insights.status.desc");

        this.setPermissionGroup(GameMode.Creative);
        this.requirePermission("*");
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

        DiscordWebhook.RunWebhookMessage();

        return CompletableFuture.completedFuture(null);
    }
}