package gui;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import java.awt.*;

public class Theme extends DefaultMetalTheme {
    public Theme() {
        super();
    }

    @Override
    protected ColorUIResource getPrimary1() {
        return new ColorUIResource(MainGui.bg);
    }

    @Override
    protected ColorUIResource getPrimary2() {
        return new ColorUIResource(MainGui.cc);
    }

    @Override
    protected ColorUIResource getPrimary3() {
        return new ColorUIResource(MainGui.bg.brighter());
    }

    @Override
    protected ColorUIResource getSecondary1() {
        return new ColorUIResource(Color.CYAN);
    }

    @Override
    protected ColorUIResource getSecondary2() {
        return new ColorUIResource(MainGui.b2g.brighter().brighter());
    }

    @Override
    protected ColorUIResource getSecondary3() {
        return new ColorUIResource(MainGui.b2g);
    }

    @Override
    protected ColorUIResource getWhite() {
        return new ColorUIResource(MainGui.bg);
    }

    @Override
    protected ColorUIResource getBlack() {
        return new ColorUIResource(MainGui.fg);
    }

    @Override
    public ColorUIResource getPrimaryControl() {
        return new ColorUIResource(MainGui.b2g);

    }

    @Override
    public ColorUIResource getPrimaryControlShadow() {
        return new ColorUIResource(MainGui.gc);
    }

    @Override
    public ColorUIResource getPrimaryControlDarkShadow() {
        return new ColorUIResource(MainGui.cc.darker());
    }

    @Override
    public ColorUIResource getPrimaryControlInfo() {
        return new ColorUIResource(MainGui.cc);
    }

    @Override
    public ColorUIResource getPrimaryControlHighlight() {
        return new ColorUIResource(MainGui.bg);
    }
}