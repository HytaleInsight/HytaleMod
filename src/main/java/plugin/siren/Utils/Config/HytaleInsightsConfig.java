package plugin.siren.Utils.Config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import plugin.siren.HytaleInsights;

public class HytaleInsightsConfig {

    public static final BuilderCodec<HytaleInsightsConfig> CODEC = BuilderCodec.builder(HytaleInsightsConfig.class, HytaleInsightsConfig::new)
            .append(new KeyedCodec<String>("Config-Information", Codec.STRING),
                    (insightConfig, ciStr, extraInfo) -> insightConfig.Information = ciStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.Information)                    // Getter
            .add()
            .append(new KeyedCodec<Integer>("ConfigVersion", Codec.INTEGER),
                    (insightConfig, cvInt, extraInfo) -> insightConfig.ConfigVersion = cvInt, // Setter
                    (insightConfig, extraInfo) -> insightConfig.ConfigVersion)                    // Getter
            .add()
            .append(new KeyedCodec<String>("PluginName", Codec.STRING),
                    (insightConfig, pnStr, extraInfo) -> insightConfig.PluginName = pnStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.PluginName)                    // Getter
            .add()
            .append(new KeyedCodec<String>("Version", Codec.STRING),
                    (insightConfig, vStr, extraInfo) -> insightConfig.Version = vStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.Version)                    // Getter
            .add()
            .append(new KeyedCodec<String>("Website", Codec.STRING),
                    (insightConfig, wStr, extraInfo) -> insightConfig.Website = wStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.Website)                    // Getter
            .add()
            .append(new KeyedCodec<String>("Download-Site", Codec.STRING),
                    (insightConfig, dsStr, extraInfo) -> insightConfig.DownloadSite = dsStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.DownloadSite)                    // Getter
            .add()
            .append(new KeyedCodec<Boolean>("Send-Discord-Webhook-Messages", Codec.BOOLEAN),
                    (insightConfig, sdwmBool, extraInfo) -> insightConfig.IfDiscord = sdwmBool, // Setter
                    (insightConfig, extraInfo) -> insightConfig.IfDiscord)                    // Getter
            .add()
            .append(new KeyedCodec<String>("Discord-Webhook", Codec.STRING),
                    (insightConfig, dsStr, extraInfo) -> insightConfig.DiscordWebhook = dsStr, // Setter
                    (insightConfig, extraInfo) -> insightConfig.DiscordWebhook)                    // Getter
            .add()
            .append(new KeyedCodec<Boolean>("New-Version-Message", Codec.BOOLEAN),
                    (insightConfig, nvmBool, extraInfo) -> insightConfig.NewVersion = nvmBool, // Setter
                    (insightConfig, extraInfo) -> insightConfig.NewVersion)                    // Getter
            .add()
            .append(new KeyedCodec<Boolean>("DebugMode", Codec.BOOLEAN),
                    (insightConfig, dmBool, extraInfo) -> insightConfig.DebugMode = dmBool, // Setter
                    (insightConfig, extraInfo) -> insightConfig.DebugMode)                    // Getter
            .add()
            .build();

    private String InformationDefault = "Confused about what one of these statement do? Go to https://www.mermaids.dev/hytale-insights/config/ or check out the Hytale Insights page on the Curseforge website and scroll down to Config Extra Info.";
    private String Information = InformationDefault;
    private final int ConfigVersionDefault = 1;
    private int ConfigVersion = ConfigVersionDefault;
    private String PluginName = "Hytale Insights";
    private String Version = HytaleInsights.getVersion();
    private String WebsiteDefault = "To-Be-Determined";
    private String Website = WebsiteDefault;
    private String DownloadSiteDefault = "https://www.curseforge.com/hytale/mods/hytaleinsights";
    private String DownloadSite = DownloadSiteDefault;
    private boolean IfDiscord = false;
    private String DiscordWebhookDefault = "PLEASE SET A DISCORD WEBHOOK IF YOU PLAN ON USING DISCORD WEBHOOK";
    private String DiscordWebhook = DiscordWebhookDefault;
    private boolean NewVersion = true;
    private boolean DebugMode = false;

    public HytaleInsightsConfig() {}

    public boolean ifConfigUpdate(){
        boolean configUpdated = false;

        if(ConfigVersionDefault > ConfigVersion){
            configUpdated = true;
            ConfigVersion = ConfigVersionDefault;
        }
        if(!Version.equalsIgnoreCase(HytaleInsights.getVersion())){
            configUpdated = true;
            Version = HytaleInsights.getVersion();
        }
        if(!Information.equalsIgnoreCase(InformationDefault)){
            configUpdated = true;
            Information = InformationDefault;
        }
        if(!Website.equalsIgnoreCase(WebsiteDefault)){
            configUpdated = true;
            Website = WebsiteDefault;
        }
        if(!DownloadSite.equalsIgnoreCase(DownloadSiteDefault)){
            configUpdated = true;
            DownloadSite = DownloadSiteDefault;
        }

        return configUpdated;
    }

    public int getConfigVersionDefault(){
        return ConfigVersionDefault;
    }

    public int getConfigVersion(){
        return ConfigVersion;
    }

    public String getPluginVersion(){
        return Version;
    }

    public void setPluginVersion(String version){
        this.Version = version;
    }

    public boolean ifDiscord(){
        return  this.IfDiscord;
    }

    public String getDiscordWebhookDefault(){
        return this.DiscordWebhookDefault;
    }

    public String getDiscordWebhook(){
        return this.DiscordWebhook;
    }

    public boolean ifNewVersion(){
        return NewVersion;
    }

    public boolean ifDebugMode(){
        return DebugMode;
    }
}
