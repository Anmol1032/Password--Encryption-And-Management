package data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class Passwords extends HashMap<String, String> implements Serializable {
    @Serial
    private final static long serialVersionUID = 2343543455345L;

    public Passwords() {
    }

}
