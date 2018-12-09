package android.com.herramientime.modules.domain.entities;


import java.util.Objects;

public class Moneda {

    private String simbolo;
    private String moneda;

    public Moneda() {
        simbolo = "";
        moneda = "";
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda1 = (Moneda) o;
        return Objects.equals(moneda, moneda1.moneda);
    }

    @Override
    public int hashCode() {

        return Objects.hash(moneda);
    }
}