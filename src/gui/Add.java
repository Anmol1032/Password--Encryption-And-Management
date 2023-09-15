package gui;

import data.MainData;
import encrypt.Encrypt;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Add extends JPanel {
    File file;
    String p1;
    String p2;

    public Add(MainGui main) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel thisPanel = this;

        JButton exitButton = new JButton("Exit");
        exitButton.setBorder(new EtchedBorder());
        exitButton.setForeground(MainGui.rc);
        exitButton.setBackground(MainGui.bg);
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Login(main));
            }
        });

        JButton enterButton = new JButton("Enter");
        enterButton.setBorder(new LineBorder(MainGui.gc));

        JLabel fileLabel = new JLabel(" File: ");
        fileLabel.setBorder(new LineBorder(MainGui.gc));
        JLabel pass1Label = new JLabel(" Password 1: ");
        pass1Label.setBorder(new LineBorder(MainGui.gc));
        JLabel pass2Label = new JLabel(" Password 2: ");
        pass2Label.setBorder(new LineBorder(MainGui.gc));

        JButton fileButton = new JButton("Choose File");
        fileButton.setBorder(new LineBorder(MainGui.gc));
        fileButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("D:\\VirtualWorld\\Java\\PasswordManager");
                int r = jFileChooser.showSaveDialog(thisPanel);

                if (r == JFileChooser.APPROVE_OPTION) {
                    fileButton.setText(jFileChooser.getSelectedFile().getAbsolutePath());
                    file = jFileChooser.getSelectedFile();
                }
            }
        });

        JPasswordField pass1Field = new JPasswordField(8);
        JPasswordField pass2Field = new JPasswordField(8);

        pass1Field.setBorder(new LineBorder(MainGui.gc));
        pass2Field.setBorder(new LineBorder(MainGui.gc));

        enterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1 = new String(pass1Field.getPassword());
                p2 = new String(pass2Field.getPassword());

                MainData mainData = new MainData();
                try {
                    Encrypt.encrypt(mainData, file, p1, p2);
                } catch (IOException ex) {
                    Misc.showError(ex, main);
                    return;
                } catch (IllegalArgumentException ex) {
                    Misc.showError("output is null", main);
                    return;
                }

                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Enter(main, file, p1, p2, mainData));
            }
        });

        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fileLabel, gbc);
        gbc.gridx = 2;
        add(fileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pass1Label, gbc);
        gbc.gridx = 2;
        add(pass1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(pass2Label, gbc);
        gbc.gridx = 2;
        add(pass2Field, gbc);

        gbc.weightx = 1;

        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(enterButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        add(exitButton, gbc);
    }
}
