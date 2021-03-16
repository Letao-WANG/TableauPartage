package Model;

import Model.MessageSockets.MessageServer;
import Model.TableSockets.TableServer;

/**
 * Create two new servers, and only Server one can exist at a time.
 * TableServer and MessageServer
 */
public class Server {
    public Server(){
        new MessageServer().start();
        new TableServer().start();
    }

    public static void main(String[] args) {
        new Server();
    }
}
