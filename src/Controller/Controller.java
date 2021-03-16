package Controller;

import java.io.IOException;

/**
 * Create two new Controller, it is also the client used by the user
 * TableController and MessageController
 */
public class Controller {
    public Controller() throws IOException {
        new TableController();
        new MessageController();
    }

    public static void main(String[] args) throws IOException {
        new Controller();
    }
}
