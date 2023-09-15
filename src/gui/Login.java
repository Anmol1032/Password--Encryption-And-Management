package gui;

import data.MainData;
import encrypt.Encrypt;
import encrypt.WrongPasswordError;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends JPanel {
    File file;
    String p1;
    String p2;

    public Login(MainGui main) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel thisPanel = this;

        JButton addButton = new JButton("Add new.");
        addButton.setBorder(new EtchedBorder());
        addButton.setForeground(MainGui.gc);
        addButton.setBackground(MainGui.bg);
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Add(main));
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBorder(new EtchedBorder());
        exitButton.setForeground(MainGui.rc);
        exitButton.setBackground(MainGui.bg);
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.dispose();
            }
        });

        JButton enterButton = new JButton("Enter");

        JLabel fileLabel = new JLabel(" File: ");
        JLabel pass1Label = new JLabel(" Password 1: ");
        JLabel pass2Label = new JLabel(" Password 2: ");

        JButton fileButton = new JButton("Choose File");
        fileButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("D:\\VirtualWorld\\Java\\PasswordManager");
                int r = jFileChooser.showOpenDialog(thisPanel);

                if (r == JFileChooser.APPROVE_OPTION) {
                    fileButton.setText(jFileChooser.getSelectedFile().getAbsolutePath());
                    file = jFileChooser.getSelectedFile();
                }
            }
        });

        JPasswordField pass1Field = new JPasswordField(8);
        JPasswordField pass2Field = new JPasswordField(8);

        enterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1 = new String(pass1Field.getPassword());
                p2 = new String(pass2Field.getPassword());

                final MainData[] mainData = new MainData[1];

                AtomicBoolean decrypted = new AtomicBoolean(false);

                Thread thread = new Thread(() -> {
                    try {
                        mainData[0] = Encrypt.decrypt(file, p1, p2);
                        decrypted.set(true);
                    } catch (WrongPasswordError ex) {
                        Misc.showError("Wrong password", main);
                    } catch (IOException ex) {
                        Misc.showError(ex, main);
                    } catch (IllegalArgumentException ex) {
                        Misc.showError("Input is null", main);
                    }
                });
                thread.start();

                thisPanel.removeAll();
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                thisPanel.add(new JLabel(new ImageIcon("Hypersphere.gif")), gbc); // todo

                AbstractAction action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (thread.isAlive()) {
                            return;
                        }

                        thisPanel.setVisible(false);
                        main.cp.remove(thisPanel);
                        if (decrypted.get()) {
                            Enter enterPane = new Enter(main, file, p1, p2, mainData[0]);
                            main.cp.add(enterPane);
                        } else {
                            Login login = new Login(main);
                            main.cp.add(login);
                        }
                        main.invalidate();
                        main.validate();
                        main.repaint();
                        ((Timer) e.getSource()).stop();
                    }
                };

                Timer timer = new Timer(100, action);
                timer.start();

                main.invalidate();
                main.validate();
                main.repaint();
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
        add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(exitButton, gbc);
    }
}
