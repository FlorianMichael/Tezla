package net.tezla.events;

import com.darkmagician6.eventapi.events.Event;

public class PressKeyEvent implements Event {

    private int keyCode;
    private int scanCode;
    private int action;
    private int modifiers;

    public PressKeyEvent(int keyCode, int scanCode, int action, int modifiers) {
        this.setKeyCode(keyCode);
        this.setScanCode(scanCode);
        this.setAction(action);
        this.setModifiers(modifiers);
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public void setScanCode(int scanCode) {
        this.scanCode = scanCode;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }
}