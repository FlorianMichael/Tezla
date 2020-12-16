package net.tezla.events;

import com.darkmagician6.eventapi.events.callables.EventCancellable;

public class ChatMessageEvent extends EventCancellable {

    private String message;

    public ChatMessageEvent(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
