package MomiNeko.OSCHub.VRChat;

import MomiNeko.OSCHub.OSCProtocol.Sender;
import com.illposed.osc.OSCMessage;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static MomiNeko.OSCHub.Config.CopyTriggerAddress;
import static MomiNeko.OSCHub.Config.PasteTriggerAddress;
import static MomiNeko.OSCHub.KeyManager.KeyTap;

public class CopyPaste {

    public static void OSCTrigger(OSCMessage oscMessage) {
        if (oscMessage.getAddress().equals(CopyTriggerAddress) && (boolean) oscMessage.getArguments()[0]) {
            CopyTrigger();
        } else if (oscMessage.getAddress().equals(PasteTriggerAddress) && (boolean) oscMessage.getArguments()[0]) {
            PasteTrigger();
        }
    }

    public static void CopyTrigger() {
        KeyTap(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
        Sender.send(CopyTriggerAddress, false);
        System.out.println("CopyToClipboard");
    }

    public static void PasteTrigger() {
        KeyTap(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        KeyTap(KeyEvent.VK_ENTER);
        Sender.send(PasteTriggerAddress, false);
        System.out.println("PasteFromClipboard");
    }

}
