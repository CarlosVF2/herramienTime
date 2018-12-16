package android.com.herramientime.modules.usuarios.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class UsuarioDetalleFragmentComponent {

    private UsuarioDetalleFragmentModule usuarioDetalleFragmentModule;

    public UsuarioDetalleFragmentComponent(UsuarioDetalleFragmentModule usuarioDetalleFragmentModule) {
        this.usuarioDetalleFragmentModule = usuarioDetalleFragmentModule;
    }

    public UsuarioDetalleFragmentModule getUsuarioDetalleFragmentModule() {
        return usuarioDetalleFragmentModule;
    }

}
