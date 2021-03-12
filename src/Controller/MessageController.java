package Controller;

import Model.Sockets.Client;
import View.MessageView;
import java.io.IOException;

/**
 * Controller Of Message
 * Multiple MessageController can exist at the same time
 */
public class MessageController {
    private Client client;
    private MessageView messageView;

    public MessageController() throws IOException {
        this.messageView = new MessageView(this);
        this.client = new Client(this);
        client.start();
    }

    public void sendMessage(String mess){
        client.sendMessage(mess);
    }

    public void setText(String text){
        messageView.setText(text);
    }

    public static void main(String[] args) throws IOException {
        new MessageController();
    }
}
