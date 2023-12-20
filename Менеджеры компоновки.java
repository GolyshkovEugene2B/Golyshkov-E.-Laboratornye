import javax.swing.*;
import java.awt.*;

public class LayoutDemo extends JFrame {
    public LayoutDemo() {
        super("Layout Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        // Устанавливаем FlowLayout для основного содержимого JFrame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        add(mainPanel);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        mainPanel.add(panel1);

        JButton button1 = new JButton("Button 1");
        mainPanel.add(button1);
    }

    public static void main(String[] args) {
        new LayoutDemo();
    }
}
