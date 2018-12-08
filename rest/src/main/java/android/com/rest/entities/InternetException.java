package android.com.rest.entities;

import java.io.IOException;

public class InternetException extends IOException {

    public InternetException(String message) {
        super(message);
    }
}
