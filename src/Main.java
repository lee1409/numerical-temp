import javax.swing.*;
import java.awt.*;

public class Main {
    Main() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1000, 500);
        jFrame.getContentPane().setLayout(null);
        Panel panel = new Panel();
        panel.setBounds(0, 0, 920,420);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        System.out.println(panel.getX());
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        jFrame.add(panel);
        jFrame.setVisible(true);
        new Thread(panel).start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
