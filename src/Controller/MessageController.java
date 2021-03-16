package Controller;

import Model.MessageSockets.MessageClient;
import View.MessageView;

import javax.swing.*;

/**
 * Controller Of Message
 * Multiple MessageController can exist at the same time
 */
public class MessageController {
    private MessageClient client;
    private MessageView messageView;

    public MessageController() {
        String name = JOptionPane.showInputDialog("Please enter your name :");
        this.messageView = new MessageView(this);
        this.client = new MessageClient(this, name);
        client.start();
    }

    public void sendMessage(String mess){
        client.sendMessage(mess);
    }

    public void setText(String text){
        messageView.setText(text);
    }

    public static void main(String[] args){
        new MessageController();
    }
}
