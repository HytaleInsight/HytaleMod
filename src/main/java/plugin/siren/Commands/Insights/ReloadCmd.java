package plugin.siren.Commands.Insights;

import com.hypixel.hytale.assetstore.AssetPack;
import com.hypixel.hytale.common.plugin.PluginManifest;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.HytaleServer;
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

public class ReloadCmd extends AbstractAsyncCommand {
    public ReloadCmd() {
        super("reload", "server.commands.hytaleinsights.insights.reload.desc");

        this.setPermissionGroup(GameMode.Creative);
        this.requirePermission("*");
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {
        HytaleInsights.getConfig().load();

        HytaleInsights.LOGGER.atInfo().log("Reloaded the config.");

        return CompletableFuture.completedFuture(null);
    }
}