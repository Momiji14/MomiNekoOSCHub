package MomiNeko.OSCHub;

import com.illposed.osc.OSCMessage;

import java.awt.Robot;
import java.awt.AWTException;

import static MomiNeko.OSCHub.Config.*;

public class DiscordMuteInVRChat {

    public static final String VRCMuteAddress = "/avatar/parameters/MuteSelf";

    public static void OSCTrigger(OSCMessage oscMessage) {
        if (DiscordMuteTriggerOnVRCMute) {
            if (oscMessage.getAddress().equals(VRCMuteAddress)) {
                ToggleMute();
            }
        } else {
            if (oscMessage.getAddress().equals(DiscordMuteAddress)) {
                ToggleMute();
            }
        }
    }

    public static void ToggleMute() {
        try {
            Robot robot = new Robot();
            robot.keyPress(DiscordMuteToggleKey);
            robot.keyRelease(DiscordMuteToggleKey);
            System.out.println("Toggle DiscordMute");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
