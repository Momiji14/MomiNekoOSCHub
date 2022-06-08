package MomiNeko.OSCHub.OSCProtocol;

import MomiNeko.OSCHub.VRChat.CopyPaste;
import MomiNeko.OSCHub.VRChat.DiscordMute;
import MomiNeko.OSCHub.VRChat.Shortcut;
import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPortIn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static MomiNeko.OSCHub.Config.*;

public class Receive {

    public static List<String> ListenerAddress = new ArrayList<>();

    public static void Connect() {
        OSCPortIn receiver;
        try {
            receiver = new OSCPortIn(OSCReceivePort);
            OSCListener listener = (date, oscMessage) -> {
                if (OSCDebug) System.out.println("Receive -> " + oscMessage.getAddress() + ": " + oscMessage.getArguments()[0]);
                DiscordMute.OSCTrigger(oscMessage);
                CopyPaste.OSCTrigger(oscMessage);
                Shortcut.OSCTrigger(oscMessage);
            };
            System.out.println("OSC-ListenerAddress[" + ListenerAddress.size() + "]:");
            int i = 1;
            for (String address : ListenerAddress) {
                receiver.addListener(address, listener);
                System.out.println(i + "." + address);
                i++;
            }
            receiver.startListening();
            System.out.println("OSC-Listener Starting!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("OSC-Listener Starting Failed...");
        }
    }
}
