package data;

import java.io.Serial;
import java.io.Serializable;

public class Notes implements Serializable {
    @Serial
    private final static long serialVersionUID = 42332443553L;

    public String data;

    public Notes(String data) {
        this.data = data;
    }
}
