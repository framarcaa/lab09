package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        final JButton save = new JButton("Save");

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.saveContent(textArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }
    public static void main(String... args) {
        new SimpleGUI(new Controller()).display();
    }
}
