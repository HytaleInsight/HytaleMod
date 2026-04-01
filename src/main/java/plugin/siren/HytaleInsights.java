package plugin.siren;

import com.hypixel.hytale.event.EventRegistration;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.CommandRegistration;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import plugin.siren.Commands.InsightsCmd;
import plugin.siren.Events.PlayerReadyEventHI;
import plugin.siren.Utils.API.HStats;
import plugin.siren.Utils.API.HytaleInsightsUpdate;
import plugin.siren.Utils.Config.HytaleInsightsConfig;
import plugin.siren.Utils.Github.GithubIgnore;

import javax.annotation.Nonnull;

public class HytaleInsights extends JavaPlugin {
    private static final String VERSION = "1.0.0";
    private static final boolean DEBUG = false;

    private static HytaleInsights plugin;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private final Config<HytaleInsightsConfig> config;

    public HytaleInsights(@Nonnull JavaPluginInit init){
        super(init);

        plugin = this;
        this.config = this.withConfig("Config", HytaleInsightsConfig.CODEC);

        new HStats(GithubIgnore.getHStatsModUUID(), VERSION);
    }

    @Override
    protected void setup(){
        LOGGER.atInfo().log("===---==---==---== Hytale Insights ==---==---==---===");
        LOGGER.atInfo().log("Hytale Insights has began to load.");

        EventRegistration<String, PlayerReadyEvent> playerReadyEventRegistration = this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, PlayerReadyEventHI::onPlayerReadyEvent);
        if(!(playerReadyEventRegistration != null && playerReadyEventRegistration.isRegistered())) {
            LOGGER.atSevere().log("Failed to register Player Ready Event.");
        }
        LOGGER.atInfo().log("Registered all Events.");

        CommandRegistration insightsCmdRegistration = this.getCommandRegistry().registerCommand(new InsightsCmd());
        if(!(insightsCmdRegistration != null && insightsCmdRegistration.isRegistered())) {
            LOGGER.atSevere().log("Failed to register Profile Command.");
        }
        LOGGER.atInfo().log("Registered all Commands.");

        config.save();
        LOGGER.atInfo().log("Loaded config settings.");
        boolean configUpdated = config.get().ifConfigUpdate();
        if(configUpdated){
            config.save();
            LOGGER.atInfo().log("Updated config to latest version.");
        }

        LOGGER.atInfo().log("Version " + VERSION + " of Hytale Insights has successfully loaded.");

        if(ifDebug()){
            LOGGER.atInfo().log("= =- -=- -=- -=- -=- -=- -=- -=- -= =");
            LOGGER.atInfo().log("Loaded Hytale Insights in Debug mode.");
        }

        HytaleInsightsUpdate.sendUpdateMessage(HytaleInsightsUpdate.Type.StartUp);

        LOGGER.atInfo().log("===---==---==---==---==---==---==---==---===");
    }

    @Override
    protected void shutdown(){
        LOGGER.atInfo().log("===---==---==---== Hytale Insights ==---==---==---===");
        LOGGER.atInfo().log("Hytale Insights has began to shutdown.");
        LOGGER.atInfo().log("Saving any necessary data.");
        LOGGER.atInfo().log("Version " + VERSION + " of Hytale Insights has successfully shutdown.");
        LOGGER.atInfo().log("===---==---==---==---==---==---==---==---===");
    }

    public static HytaleInsights get(){
        return plugin;
    }

    public static String getVersion(){
        return VERSION;
    }

    public static Config<HytaleInsightsConfig> getConfig(){
        return plugin.config;
    }

    public static boolean ifDebug(){
        return plugin.DEBUG || plugin.config.get().ifDebugMode();
    }
}
