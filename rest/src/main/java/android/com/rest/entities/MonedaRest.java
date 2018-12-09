package android.com.rest.entities;

import com.google.gson.annotations.SerializedName;

public class MonedaRest {

    @SerializedName("simbolo")
    private String simbolo;

    @SerializedName("moneda")
    private String moneda;

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public String toString() {
        return
                "MonedaRest{" +
                        "simbolo = '" + simbolo + '\'' +
                        ",moneda = '" + moneda + '\'' +
                        "}";
    }
}