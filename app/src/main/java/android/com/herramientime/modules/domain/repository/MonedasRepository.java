package android.com.herramientime.modules.domain.repository;

import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.rest.entities.InternetException;

import java.util.List;

public interface MonedasRepository {
    List<Moneda> getMonedas() throws InternetException;
}
