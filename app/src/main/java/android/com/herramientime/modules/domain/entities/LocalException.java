package android.com.herramientime.modules.domain.entities;

/**
 * Created by carlo on 06/11/2018.
 */

public class LocalException extends Exception {

    private String descripcionError;
    private String codeError;

    public LocalException(String message) {
        super(message);
    }

    //region GET

    public String getDescripcionError() {
        return descripcionError;
    }

    public String getCodeError() {
        return codeError;
    }

    //endregion GET

    //region SET

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }


    //endregion SET

}
