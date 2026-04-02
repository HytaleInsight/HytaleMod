package plugin.siren.Commands;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import plugin.siren.Commands.Insights.*;

public class InsightsCmd extends AbstractCommandCollection {
    public InsightsCmd() {
        super("insights", "server.commands.hytaleinsights.insights.desc");

        this.addSubCommand(new ReloadCmd());
        this.addSubCommand(new StatusCmd());

        this.setPermissionGroup(GameMode.Creative);
        this.requirePermission("*");
    }
}