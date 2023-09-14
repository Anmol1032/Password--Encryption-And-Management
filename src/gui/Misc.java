package gui;

import javax.swing.*;

public class Misc {
    public static void showError(Exception e, MainGui main) {
        showError(e.getMessage(), main);
    }

    public static void showError(String e, MainGui main) {
        JDialog jDialog = new JDialog(main, true);
        jDialog.add(new JLabel("<html>" + e.replaceAll("\\n", "<br/>" + "<html>")));
        jDialog.setUndecorated(true);
        jDialog.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
        jDialog.pack();

        jDialog.setLocationRelativeTo(main);
        jDialog.setLocation(-jDialog.getWidth() / 4 + jDialog.getX(), -jDialog.getHeight() / 4 + jDialog.getY());

        jDialog.setVisible(true);
    }
}
