package MomiNeko.OSCHub;

import MomiNeko.OSCHub.OSCProtocol.Receive;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties config = new Properties();
    public static String DiscordMuteAddress;
    public static int DiscordMuteToggleKey;
    public static boolean DiscordMuteTriggerOnVRCMute;

    public static String getConfigData(String Path) {
        return (String) config.get(Path);
    }

    public static void load() {
        try {
            System.out.println("Config Loading...");
            config.load(new FileInputStream("config.properties"));

            DiscordMuteAddress = getConfigData("DiscordMuteAddress");
            Receive.ListenerAddress.add(DiscordMuteAddress);
            System.out.println("DiscordMuteAddress: " + DiscordMuteAddress);

            DiscordMuteToggleKey = Integer.parseInt(getConfigData("DiscordMuteToggleKey"));
            System.out.println("DiscordMuteToggleKey: " + DiscordMuteToggleKey);

            DiscordMuteTriggerOnVRCMute = Boolean.parseBoolean(getConfigData("DiscordMuteTriggerOnVRCMute"));
            System.out.println("DiscordMuteTriggerOnVRCMute: " + DiscordMuteTriggerOnVRCMute);

            System.out.println("Config Loading Completed!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config Loading Failed...");
        }
    }
}
