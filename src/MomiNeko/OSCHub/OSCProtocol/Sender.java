package MomiNeko.OSCHub.OSCProtocol;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.io.IOException;
import java.net.SocketException;

import static MomiNeko.OSCHub.Config.*;

public class Sender {
    public static OSCPortOut sender;
    public static void Connect() {
        try {
            sender = new OSCPortOut(OSCSenderHost, OSCSenderPort);
            System.out.println("OSC-Connected!");
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("OSC-Connect Failed...");
        }
    }

    public static void send(String Address, int _int) {
        Object[] args = new Object[1];
        args[0] = _int;
        send(Address, args);
    }
    public static void send(String Address, boolean _bool) {
        Object[] args = new Object[1];
        args[0] = _bool;
        send(Address, args);
    }
    public static void send(String Address, Object[] args) {
        if (sender != null) {
            try {
                OSCMessage oscMessage = new OSCMessage(Address, args);
                sender.send(oscMessage);
                if (OSCDebug) System.out.println("Send -> " + oscMessage.getAddress() + ": " + oscMessage.getArguments()[0]);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("OSC-Send Failed -> " + Address);
            }
        } else {
            System.out.println("OSC-Connect Failed...");
        }
    }
}
