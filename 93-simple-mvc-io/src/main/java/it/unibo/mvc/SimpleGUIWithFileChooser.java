package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel1 = new JPanel(new BorderLayout());
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

        panel1.add(textArea, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);



        final JPanel panel2 = new JPanel(new BorderLayout());
        final JTextField text = new JTextField(controller.getCurrentFilePath());
        final JButton browse = new JButton("Browse...");

        text.setEditable(false);

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(controller.getCurrentFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = fileChooser.getSelectedFile();
                        controller.setDestination(newDest);
                        text.setText(newDest.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
            
        });

        panel2.add(text, BorderLayout.CENTER);
        panel2.add(browse, BorderLayout.LINE_END);
        panel1.add(panel2, BorderLayout.NORTH);

        frame.setContentPane(panel1);
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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}
