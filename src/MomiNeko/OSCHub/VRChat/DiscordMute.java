package MomiNeko.OSCHub.VRChat;

import MomiNeko.OSCHub.OSCProtocol.Sender;
import com.illposed.osc.OSCMessage;

import java.awt.event.KeyEvent;

import static MomiNeko.OSCHub.Config.*;
import static MomiNeko.OSCHub.KeyManager.KeyTap;

public class DiscordMute {

    public static void OSCTrigger(OSCMessage oscMessage) {
        if (oscMessage.getAddress().equals(VRCMuteAddress)) {
            Parameter.MuteSelf = (boolean) oscMessage.getArguments()[0];
        } else if (oscMessage.getAddress().equals(DiscordMuteAddress) && (boolean) oscMessage.getArguments()[0]) {
            ToggleMute();
        } else if (DiscordMuteTriggerOnVRCMute && oscMessage.getAddress().equals(VRCMuteAddress)) {
            ToggleMute();
        }
    }

    public static void ToggleMute() {
        KeyTap(DiscordMuteToggleKey);
        if (DiscordMuteToggleToVRCMute) {
            Sender.send(VRCMuteToggleAddress, false);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sender.send(VRCMuteToggleAddress, true);
        }
        Sender.send(DiscordMuteAddress, false);
        System.out.println("Toggle DiscordMute");
    }
}
