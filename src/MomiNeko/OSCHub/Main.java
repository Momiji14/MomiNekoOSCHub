package MomiNeko.OSCHub;

import MomiNeko.OSCHub.OSCProtocol.*;

import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {
        System.out.println("《MomiNeko OSC Hub》");
        KeyManager.start();
        Config.load();
        Sender.Connect();
        Receive.Connect();
        Router.start();
    }
}
