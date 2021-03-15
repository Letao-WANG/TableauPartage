package Controller;

import java.io.IOException;

public class Controller {
    private TableController tableController;
    private MessageController messageController;

    public Controller() throws IOException {
        this.tableController = new TableController();
        this.messageController = new MessageController();
    }

    public static void main(String[] args) throws IOException {
        new Controller();
    }
}
