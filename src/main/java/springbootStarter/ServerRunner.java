package springbootStarter;

import data.fileHelper.FileSystemInitializer;

import javax.swing.*;
import java.awt.Font;


public class ServerRunner {

    private JFrame frame;

    public void run(){
        FileSystemInitializer initializer = new FileSystemInitializer();
        initializer.initFileSystem();

        frame = new JFrame("ServerFrame");
        frame.setSize(500, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JTextField textField = new JTextField("服务器运行中……");
        textField.setFont(new Font("Serif", Font.PLAIN, 56));
        textField.setEditable(false);
        panel.add(textField);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
