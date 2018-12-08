package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.rest.entities.MonedaRest;

import java.util.ArrayList;
import java.util.List;

public class ProcessorMoneda {


    public List<Moneda> convertFrom(List<MonedaRest> herramientaRests) {
        List<Moneda> monedas = new ArrayList<>();
        for (MonedaRest monedaRest : herramientaRests) {
            monedas.add(convertFrom(monedaRest));
        }
        return monedas;
    }

    public Moneda convertFrom(MonedaRest from) {
        Moneda moneda = new Moneda();
        moneda.setMoneda(from.getMoneda());
        moneda.setSimbolo(from.getSimbolo());
        return moneda;
    }
}
