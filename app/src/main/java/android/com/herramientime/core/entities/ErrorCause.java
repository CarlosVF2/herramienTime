package android.com.herramientime.core.entities;

import java.util.concurrent.ExecutionException;

public class ErrorCause {

    public static String getCause(Throwable e) {
        if (e instanceof ExecutionException) {
            e = e.getCause();
        }

        return e.getCause() != null && e.getCause().getMessage() != null && e.getCause().getMessage().trim().length() > 0 ? e.getCause().getMessage() : e.getMessage();
    }
}
