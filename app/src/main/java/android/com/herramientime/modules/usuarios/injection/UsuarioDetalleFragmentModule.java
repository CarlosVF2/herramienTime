package android.com.herramientime.modules.usuarios.injection;

import android.com.herramientime.injection.InteractorFactory;
import android.com.herramientime.modules.usuarios.interactor.UsuarioDetalleFragmentInteractor;
import android.content.res.Resources;

/**
 * Created by carlo on 06/11/2018.
 */

public class UsuarioDetalleFragmentModule {

    private final UsuarioDetalleFragmentInteractor usuarioDetalleFragmentInteractor;
    private final Resources resources;

    public UsuarioDetalleFragmentModule(InteractorFactory interactorFactory, Resources resources) {
        this.resources = resources;
        this.usuarioDetalleFragmentInteractor = interactorFactory.getUsuarioDetalleFragmentInteractor();
    }

    public UsuarioDetalleFragmentInteractor getUsuarioDetalleFragmentInteractor() {
        return usuarioDetalleFragmentInteractor;
    }

    public Resources getResources() {
        return resources;
    }
}
