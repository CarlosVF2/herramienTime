package android.com.herramientime.modules.reservar.view;

import android.com.herramientime.core.view.MvpFragment;

import java.util.Calendar;

/**
 * Created by carlo on 06/11/2018.
 */

public interface ReservaFragment extends MvpFragment {

    void showDatePickerFechaInicial(Calendar fechaElegida);

    void showDatePickerFechaFinal(Calendar fechaElegida);

    void setFechaInicial(String fechaInicial);

    void setFechaFinal(String fechaFinal);

    void setDescripcionText(String descripcion);

    void setHintText(String experiencia);
}
