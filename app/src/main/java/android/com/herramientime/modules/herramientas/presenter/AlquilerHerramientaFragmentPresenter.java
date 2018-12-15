package android.com.herramientime.modules.herramientas.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;
import android.os.Bundle;

/**
 * Created by carlo on 06/11/2018.
 */

public interface AlquilerHerramientaFragmentPresenter extends MvpFragmentPresenter {
    void setTitulo(String titulo);

    void setDescripcion(String descripcion);

    void setPrecio(String precio);

    void onClickConfirmar();

    void onClickMakePhoto();

    void onItemSimbolosSelected(int i);

    void onItemCateogiraSelected(int i);

    void onViewStateRestored(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);

    void showPhoto();
}
