package MomiNeko.OSCHub.VRChat;

import MomiNeko.OSCHub.Config;
import MomiNeko.OSCHub.OSCProtocol.Sender;
import com.illposed.osc.OSCMessage;

import static MomiNeko.OSCHub.Config.*;
import static MomiNeko.OSCHub.KeyManager.KeyTap;

public class Shortcut {

    public static void OSCTrigger(OSCMessage oscMessage) {
        String address = oscMessage.getAddress();
        if (ShortcutAddress.containsKey(address) && (boolean) oscMessage.getArguments()[0]) {
            KeyTap(ShortcutAddress.get(address));
            Sender.send(address, false);
            System.out.println("Shortcut Trigger");
        }
    }
}
