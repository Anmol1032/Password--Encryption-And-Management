package gui;

import data.MainData;
import encrypt.Encrypt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Edit extends JPanel {
    public Edit(MainGui main, File file, String p1, String p2, MainData mainData, MainData mainData0) {
        setLayout(new GridBagLayout());
        JPanel thisPanel = this;
        GridBagConstraints gbc = new GridBagConstraints();

        JList<Object> list = new JList<>(mainData.passwords.keySet().toArray());
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(new LineBorder(MainGui.rc));

        list.addListSelectionListener(e -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            JDialog jDialog = new JDialog(main, true);
            jDialog.setLayout(new GridLayout(5, 2));

            String value = list.getSelectedValue().toString();
            JLabel jLabel1 = new JLabel("Id: ");
            jLabel1.setForeground(MainGui.cc);
            jDialog.add(jLabel1);

            JLabel id = new JLabel(value);
            jDialog.add(id);

            String password = mainData.passwords.get(value);
            JLabel jLabel2 = new JLabel("Password: ");
            jDialog.add(jLabel2);

            JTextField passwordField = new JTextField(password);
            jDialog.add(passwordField);

            jDialog.setUndecorated(true);
            jDialog.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);

            jDialog.add(new JSeparator());
            jDialog.add(new JSeparator());

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBackground(MainGui.bg);
            cancelButton.setBorder(new EmptyBorder(0, 0, 0, 0));
            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jDialog.dispose();
                }
            });
            jDialog.add(cancelButton);

            JButton conformButton = new JButton("Conform");
            conformButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainData.passwords.put(value, passwordField.getText());
                    jDialog.dispose();

                    thisPanel.setVisible(false);
                    main.cp.remove(thisPanel);
                    main.cp.add(new Edit(main, file, p1, p2, mainData, mainData0));
                }
            });
            jDialog.add(conformButton);

            jDialog.add(new JSeparator());
            JButton deleteButton = new JButton("Delete");
            deleteButton.setBackground(MainGui.rc.darker().darker());
            deleteButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainData.passwords.remove(value);
                    jDialog.dispose();

                    thisPanel.setVisible(false);
                    main.cp.remove(thisPanel);
                    main.cp.add(new Edit(main, file, p1, p2, mainData, mainData0));
                }
            });
            jDialog.add(deleteButton);

            jDialog.pack();

            jDialog.setLocationRelativeTo(main);
            jDialog.setLocation(-jDialog.getWidth() / 4 + jDialog.getX(), -jDialog.getHeight() / 4 + jDialog.getY());

            jDialog.setVisible(true);
        });

        JButton addButton = new JButton("add");
        addButton.setBorder(new LineBorder(MainGui.rc));
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                JDialog jDialog = new JDialog(main, true);
                jDialog.setLayout(new GridLayout(4, 2));

                JLabel jLabel1 = new JLabel("Id: ");
                jDialog.add(jLabel1);

                JTextField idTextField = new JTextField(8);
                jDialog.add(idTextField);


                JLabel jLabel2 = new JLabel("Password: ");
                jDialog.add(jLabel2);

                JTextField passwordField = new JTextField(8);
                jDialog.add(passwordField);

                jDialog.setUndecorated(true);
                jDialog.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);

                jDialog.add(new JSeparator());
                jDialog.add(new JSeparator());

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBackground(MainGui.bg);
                cancelButton.setBorder(new EmptyBorder(0, 0, 0, 0));
                cancelButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jDialog.dispose();
                    }
                });
                jDialog.add(cancelButton);

                JButton conformButton = new JButton("Conform");
                conformButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mainData.passwords.put(idTextField.getText(), passwordField.getText());
                        jDialog.dispose();

                        thisPanel.setVisible(false);
                        main.cp.remove(thisPanel);
                        main.cp.add(new Edit(main, file, p1, p2, mainData, mainData0));
                    }
                });
                jDialog.add(conformButton);

                jDialog.pack();

                jDialog.setLocationRelativeTo(main);
                jDialog.setLocation(-jDialog.getWidth() / 4 + jDialog.getX(), -jDialog.getHeight() / 4 + jDialog.getY());

                jDialog.setVisible(true);
            }
        });

        JTextArea jTextArea = new JTextArea(mainData.notes.data);
        jTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                mainData.notes.data = jTextArea.getText();
            }
        });
        JScrollPane scrollPane2 = new JScrollPane(jTextArea);
        scrollPane2.setBorder(new LineBorder(MainGui.rc));

        JButton passButton = new JButton("Change Passwords");
        passButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        passButton.setForeground(MainGui.rc.brighter());
        passButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passButton.setBackground(MainGui.rc.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                passButton.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                passButton.setBackground(MainGui.cc.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                passButton.setBackground(MainGui.cc.darker());
            }
        });
        passButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new ChangePass(main, file, p1, p2, mainData, mainData0));
            }
        });

        JButton editButton = new JButton("Conform and Edit mode");
        editButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        editButton.setForeground(MainGui.rc);
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editButton.setBackground(MainGui.cc.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editButton.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                editButton.setBackground(MainGui.cc.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                editButton.setBackground(MainGui.cc.darker());
            }
        });

        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainData.notes.data = jTextArea.getText();
                    Encrypt.encrypt(mainData, file, p1, p2);
                } catch (IOException ex) {
                    Misc.showError(ex, main);
                }

                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Enter(main, file, p1, p2, mainData));
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(MainGui.cc.brighter());
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Enter(main, file, p1, p2, mainData0));
            }
        });
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(MainGui.rc.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                exitButton.setBackground(MainGui.rc.brighter());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                exitButton.setBackground(MainGui.rc.brighter());
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JLabel(file.getName()), gbc);

        gbc.gridy = 1;
        gbc.weighty = 18;
        add(scrollPane, gbc);

        gbc.weighty = 1;
        gbc.gridy = 2;
        add(addButton, gbc);

        gbc.gridy = 3;
        add(new JSeparator(), gbc);

        gbc.gridy = 4;
        gbc.weighty = 18;
        add(scrollPane2, gbc);

        gbc.weighty = 1;
        gbc.gridy = 5;
        add(new JSeparator(), gbc);

        gbc.gridy = 6;
        add(passButton, gbc);

        gbc.gridy = 7;
        add(editButton, gbc);

        gbc.gridy = 8;
        add(exitButton, gbc);
    }
}
