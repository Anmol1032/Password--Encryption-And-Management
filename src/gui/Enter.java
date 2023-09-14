package gui;

import data.MainData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;

public class Enter extends JPanel {
    public Enter(MainGui main, File file, String p1, String p2, MainData mainData) {
        setLayout(new GridBagLayout());
        JPanel thisPanel = this;
        GridBagConstraints gbc = new GridBagConstraints();

        JList<Object> list = new JList<>(mainData.passwords.keySet().toArray());
        JScrollPane scrollPane = new JScrollPane(list);

        list.addListSelectionListener(e -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            JDialog jDialog = new JDialog(main, true);
            jDialog.setLayout(new GridLayout(2, 2));

            String value = list.getSelectedValue().toString();
            JLabel jLabel1 = new JLabel("Id: " + value);
            jLabel1.setForeground(MainGui.cc);
            jDialog.add(jLabel1);

            JButton jButton1 = new JButton("Copy");
            jButton1.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringSelection selection = new StringSelection(value);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                }
            });
            jDialog.add(jButton1);

            String password = mainData.passwords.get(value);
            JLabel jLabel2 = new JLabel("Password: " + password);
            jDialog.add(jLabel2);

            JButton jButton2 = new JButton("Copy");
            jButton2.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringSelection selection = new StringSelection(password);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                }
            });
            jDialog.add(jButton2);

            jDialog.setUndecorated(true);
            jDialog.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

            jDialog.pack();

            jDialog.setLocationRelativeTo(main);
            jDialog.setLocation(-jDialog.getWidth() / 4 + jDialog.getX(), -jDialog.getHeight() / 4 + jDialog.getY());

            jDialog.setVisible(true);
        });


        JLabel jLabel = new JLabel("<html> " + mainData.notes.data.replaceAll("\\n", "<br/>") + "<html/>");
        JScrollPane scrollPane2 = new JScrollPane(jLabel);

        JButton editButton = new JButton("Edit");
        editButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        editButton.setForeground(MainGui.rc);
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editButton.setBackground(MainGui.cc.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                editButton.setBackground(UIManager.getColor("control"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                editButton.setBackground(MainGui.cc.brighter());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                editButton.setBackground(MainGui.cc.brighter());
            }
        });

        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);

                MainData mainData0 = new MainData();
                mainData0.notes.data = mainData.notes.data;
                mainData0.passwords.putAll(mainData.passwords);

                main.cp.add(new Edit(main, file, p1, p2, mainData, mainData0));
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(MainGui.cc.brighter());
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Login(main));
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
        add(new JSeparator(), gbc);

        gbc.gridy = 3;
        gbc.weighty = 18;
        add(scrollPane2, gbc);

        gbc.weighty = 1;
        gbc.gridy = 4;
        add(new JSeparator(), gbc);

        gbc.gridy = 5;
        add(editButton, gbc);

        gbc.gridy = 6;
        add(exitButton, gbc);
    }
}
