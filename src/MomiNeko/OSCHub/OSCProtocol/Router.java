package MomiNeko.OSCHub.OSCProtocol;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPortIn;

import java.io.IOException;

import static MomiNeko.OSCHub.Config.OSCRouterPort;

public class Router {

    public static void start() {
        for (int port : OSCRouterPort) {
            if (port != -1) Connect(port);
        }
    }

    public static void Connect(int port) {
        try {
            OSCPortIn receiver = new OSCPortIn(port);
            OSCListener listener = (date, oscMessage) -> {
                Sender.send(oscMessage.getAddress(), oscMessage.getArguments());
            };
            receiver.addListener("/*", listener);
            receiver.startListening();
            System.out.println("OSC-Router[" + port + "] Starting!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("OSC-Router[" + port + "] Starting Failed...");
        }
    }
}
