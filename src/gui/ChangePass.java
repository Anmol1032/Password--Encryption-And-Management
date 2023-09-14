package gui;

import data.MainData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ChangePass extends JPanel {

    public ChangePass(MainGui main, File file, String p1, String p2, MainData mainData, MainData mainData0) {
        setLayout(new GridBagLayout());
        JPanel thisPanel = this;
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField p1Field = new JTextField(p1);
        JTextField p2Field = new JTextField(p2);

        JButton conform = new JButton("Save password");
        conform.setBorder(new LineBorder(MainGui.rc));
        conform.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Edit(main, file, p1Field.getText(), p2Field.getText(), mainData, mainData0));
            }
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                main.cp.remove(thisPanel);
                main.cp.add(new Edit(main, file, p1, p2, mainData, mainData0));
            }
        });


        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        gbc.gridy = 0;
        add(p1Field, gbc);
        gbc.gridy = 1;
        add(p2Field, gbc);
        gbc.gridy = 2;
        add(new JSeparator(), gbc);
        gbc.gridy = 3;
        add(conform, gbc);
        gbc.gridy = 4;
        add(exit, gbc);
    }
}
