package android.com.herramientime.modules.domain.entities;

/**
 * Created by carlo on 06/11/2018.
 */

public class ResultsException extends Exception {

    private String descripcionError;
    private String codeError;

    public ResultsException(String message) {
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
