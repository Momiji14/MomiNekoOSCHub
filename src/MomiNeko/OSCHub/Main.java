package MomiNeko.OSCHub;

import MomiNeko.OSCHub.OSCProtocol.Receive;

public class Main {

    public static void main(String[] args) {
        Config.load();
        Receive.Connect();
    }
}
