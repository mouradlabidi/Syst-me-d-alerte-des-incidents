package dz.appdist.classjava;


import java.util.ArrayList;
import java.util.List;

public class ChatModel {
	private List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
