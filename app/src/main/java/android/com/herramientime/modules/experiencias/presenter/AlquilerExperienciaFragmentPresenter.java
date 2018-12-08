package android.com.herramientime.modules.experiencias.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

/**
 * Created by carlo on 06/11/2018.
 */

public interface AlquilerExperienciaFragmentPresenter extends MvpFragmentPresenter {
    void setTitulo(String titulo);

    void setDescripcion(String descripcion);

    void setPrecio(String precio);

    void onClickConfirmar();
}
