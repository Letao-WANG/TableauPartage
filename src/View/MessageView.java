package View;

import javax.swing.*;

public class MessageView extends JPanel {

    private MessageDisplayView messageDisplayView;

    public MessageView() {
        messageDisplayView = new MessageDisplayView();
        add(messageDisplayView);
    }
}
