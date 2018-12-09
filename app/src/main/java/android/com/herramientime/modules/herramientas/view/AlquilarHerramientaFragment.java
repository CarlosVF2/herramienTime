package android.com.herramientime.modules.herramientas.view;

import android.com.herramientime.core.view.MvpFragment;
import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public interface AlquilarHerramientaFragment extends MvpFragment {
    void setVisibilityMenuItemConfirmar(boolean visibilitMenuItemConfirmar);

    void launchIntentPhoto();

    String createPhoto(String id, Bitmap bitmap);

    Bitmap getBitmapImage();

    void setAdapterSpinnerMoneda(List<String> valuesAdapterMoneda);

    void setAdapterSpinnerCategoria(List<String> valuesAdapterCategoria);
}
