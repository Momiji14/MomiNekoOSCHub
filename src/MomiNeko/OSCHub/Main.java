package MomiNeko.OSCHub;

import com.illposed.osc.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new Thread(new Connect()).start();
    }


    static String DiscordMuteAddress = "/avatar/parameters/DiscordMute";
    static class Connect implements Runnable {
        public void run() {
            OSCPortIn receiver;
            try {
                receiver = new OSCPortIn(9001);
                System.out.println("Connect Success");

                OSCListener listener = (date, oscMessage) -> {
                    if (oscMessage.getAddress().equals(DiscordMuteAddress)) {
                        try {
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_END);
                            robot.keyRelease(KeyEvent.VK_END);
                            System.out.println("Toggle DiscordMute");
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    }
                };
                receiver.addListener(DiscordMuteAddress, listener);
                receiver.startListening();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Connect Fail");
            }
        }
    }
}
