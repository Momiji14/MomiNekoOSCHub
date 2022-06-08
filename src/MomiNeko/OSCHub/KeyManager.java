package MomiNeko.OSCHub;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KeyManager {

    static Robot robot;

    public static void start() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void KeyTap(List<Integer> keys) {
        for (int key : keys) {
            robot.keyPress(key);
        }
        for (int key : keys) {
            robot.keyRelease(key);
        }
    }

    public static void KeyTap(int key) {
        List<Integer> keys = new ArrayList<>();
        keys.add(key);
        KeyTap(keys);
    }

    public static void KeyTap(int key, int key2) {
        List<Integer> keys = new ArrayList<>();
        keys.add(key);
        keys.add(key2);
        KeyTap(keys);
    }
}
