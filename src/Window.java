import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window  extends JFrame{
    public  Window(GamePanel gamePanel){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setResizable(true);
        add(gamePanel);
        gamePanel.setVisible(true);
        setVisible(true);
    }
}
