package MomiNeko.OSCHub;

import MomiNeko.OSCHub.OSCProtocol.Receive;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class Config {
    private static final Properties config = new Properties();

    public static boolean OSCDebug;
    public static InetAddress OSCSenderHost;
    public static int OSCSenderPort;
    public static int OSCReceivePort;
    public static List<Integer> OSCRouterPort = new ArrayList<>();

    public static String DiscordMuteAddress;
    public static List<Integer> DiscordMuteToggleKey = new ArrayList<>();
    public static boolean DiscordMuteTriggerOnVRCMute;
    public static boolean DiscordMuteToggleToVRCMute;
    public static final String VRCMuteAddress = "/avatar/parameters/MuteSelf";
    public static final String VRCMuteToggleAddress = "/input/Voice";

    public static String CopyTriggerAddress;
    public static String PasteTriggerAddress;

    public static HashMap<String, List<Integer>> ShortcutAddress = new HashMap<>();

    public static String OBSRecAddress;
    public static List<Integer> OBSRecToggleKey = new ArrayList<>();

    public static String getConfigData(String Path) {
        return (String) config.get(Path);
    }

    public static void load() {
        try {
            System.out.println("Config Loading...");
            config.load(new FileInputStream("config.properties"));

            OSCDebug = Boolean.parseBoolean(getConfigData("OSCDebug"));
            OSCSenderHost = InetAddress.getByName(getConfigData("OSCSenderHost"));
            OSCSenderPort = Integer.parseInt(getConfigData("OSCSenderPort"));
            OSCReceivePort = Integer.parseInt(getConfigData("OSCReceivePort"));
            for (String str : getConfigData("OSCRouterPort").split(",")) {
                OSCRouterPort.add(Integer.parseInt(str));
            }
            System.out.println("OSCHubSetting:");
            System.out.println("・OSCSenderHost: " + OSCSenderHost);
            System.out.println("・OSCSenderPort: " + OSCSenderPort);
            System.out.println("・OSCReceivePort: " + OSCReceivePort);
            System.out.println("・OSCRouterPort: " + OSCRouterPort);

            DiscordMuteAddress = getConfigData("DiscordMuteAddress");
            for (String str : getConfigData("DiscordMuteToggleKey").split(",")) {
                DiscordMuteToggleKey.add(Integer.parseInt(str));
            }
            DiscordMuteTriggerOnVRCMute = Boolean.parseBoolean(getConfigData("DiscordMuteTriggerOnVRCMute"));
            DiscordMuteToggleToVRCMute = Boolean.parseBoolean(getConfigData("DiscordMuteToggleToVRCMute"));
            Receive.ListenerAddress.add(DiscordMuteAddress);
            if (DiscordMuteTriggerOnVRCMute || DiscordMuteToggleToVRCMute) Receive.ListenerAddress.add(VRCMuteAddress);
            System.out.println("DiscordMuteInVRChat:");
            System.out.println("・DiscordMuteAddress: " + DiscordMuteAddress);
            System.out.println("・DiscordMuteToggleKey: " + DiscordMuteToggleKey);
            System.out.println("・DiscordMuteTriggerOnVRCMute: " + DiscordMuteTriggerOnVRCMute);
            System.out.println("・DiscordMuteToggleToVRCMute: " + DiscordMuteToggleToVRCMute);

            System.out.println("CopyPasteInVRChat:");
            CopyTriggerAddress = getConfigData("CopyTriggerAddress");
            PasteTriggerAddress = getConfigData("PasteTriggerAddress");
            Receive.ListenerAddress.add(CopyTriggerAddress);
            Receive.ListenerAddress.add(PasteTriggerAddress);
            System.out.println("・CopyTriggerAddress: " + CopyTriggerAddress);
            System.out.println("・PasteTriggerAddress: " + PasteTriggerAddress);

            System.out.println("Shortcut:");
            for (int i = 0; i < 256; i++) {
                String address = getConfigData("ShortcutAddress." + i);
                if (address != null) {
                    String key = getConfigData("ShortcutKey." + i);
                    List<Integer> keys = new ArrayList<>();
                    if (key != null) for (String str : key.split(",")) {
                        keys.add(Integer.parseInt(str));
                    }
                    if (keys.size() > 0) {
                        ShortcutAddress.put(address, keys);
                        Receive.ListenerAddress.add(address);
                        System.out.println("・ShortcutAddress[" + i + "]: " + address);
                        System.out.println("・ShortcutKey[" + i + "]: " + key);
                    } else {
                        System.out.println("・Shortcut[" + i + "]はAddressが設定されていますがKeysが未設定か不正なKeyIDです");
                    }
                }
            }

            System.out.println("Config Loading Completed!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config Loading Failed...");
        }
    }
}
