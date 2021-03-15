package Model;

import Model.MessageSockets.MessageServer;
import Model.TableSockets.TableServer;

public class Server {
    private TableServer tableServer;
    private MessageServer messageServer;

    public Server(){
        this.messageServer = new MessageServer();
        this.tableServer = new TableServer();
        messageServer.start();
        tableServer.start();
    }

    public static void main(String[] args) {
        new Server();
    }
}
