package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class MainGui extends JFrame {
    static final Color bg = new Color(0x000000);
    static final Color b2g = new Color(0x000B1E);
    static final Color fg = new Color(0xEEFFFF);
    static final Color gc = new Color(0xA0FF00);
    static final Color rc = new Color(0xFF8679);
    static final Color cc = new Color(0x00FFFF);

    public final JPanel cp;

    public MainGui() {
        cp = new JPanel(new GridLayout());

        JLabel image = new JLabel(new ImageIcon("Hypersphere.gif"));
        cp.add(image);

        setContentPane(cp);
        cp.setBorder(new LineBorder(rc, 3, true));
        getRootPane().setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, gc, gc.brighter()), new LineBorder(cc, 3, true)));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        pack();
        Dimension d = new Dimension(1200, 500);
        setPreferredSize(d);
        setSize(d);

        MetalLookAndFeel.setCurrentTheme(new Theme());
        //UIManager code snippet to set the look and feel them for the window
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            UIManager.put("Panel.background", bg);
            Font font = new Font("default", Font.PLAIN, 24);
            UIManager.put("Button.font", font);
            UIManager.put("PasswordField.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("Label.font", font);
            UIManager.put("List.font", font.deriveFont(18f));
        } catch (Exception e) {
            Misc.showError(e, this);
        }

        SwingUtilities.updateComponentTreeUI(this);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            cp.remove(image);

            Login loginPane = new Login(this);
            cp.add(loginPane);

            this.invalidate();
            this.validate();
            this.repaint();

        });
        thread.start();


        setVisible(true);
    }


}
