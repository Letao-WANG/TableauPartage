package Controller;

import Model.Sockets.Client;
import Model.Sockets.Server;
import View.MessageView;
import java.io.IOException;

public class MessageController {
    private Server server;
    private Client client;
    private MessageView messageView;

    public MessageController() throws IOException {
        this.server = new Server();
        this.client = new Client();
//        this.messageView = new MessageView();
    }

    public static void main(String[] args) throws IOException {
        new MessageController();
    }
}
