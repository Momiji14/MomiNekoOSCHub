package MomiNeko.OSCHub.OSCProtocol;

import MomiNeko.OSCHub.DiscordMuteInVRChat;
import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPortIn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Receive {

    public static List<String> ListenerAddress = new ArrayList<String>();

    public static void Connect() {
        OSCPortIn receiver;
        try {
            receiver = new OSCPortIn(9001);
            OSCListener listener = (date, oscMessage) -> {
                DiscordMuteInVRChat.OSCTrigger(oscMessage);
            };
            System.out.println("ListenerAddress[" + ListenerAddress.size() + "]:");
            int i = 1;
            for (String address : ListenerAddress) {
                receiver.addListener(address, listener);
                System.out.println(i + "." + address);
                i++;
            }
            receiver.startListening();
            System.out.println("Listener Starting!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Listener Starting Failed...");
        }
    }
}
