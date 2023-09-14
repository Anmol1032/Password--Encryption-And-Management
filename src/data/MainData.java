package data;

import java.io.Serial;
import java.io.Serializable;

public class MainData implements Serializable {
    @Serial
    private final static long serialVersionUID = 1490198041940493L;

    public final Notes notes;
    public final Passwords passwords;

    public MainData() {
        this.notes = new Notes("");
        this.passwords = new Passwords();
    }
}
