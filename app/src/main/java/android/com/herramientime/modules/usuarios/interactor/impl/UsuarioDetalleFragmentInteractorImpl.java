package android.com.herramientime.modules.usuarios.interactor.impl;

import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.herramientime.modules.usuarios.interactor.UsuarioDetalleFragmentInteractor;
import android.com.herramientime.modules.usuarios.repository.UsuarioDetalleFragmentRepository;

import com.seidor.core.task.executor.future.ResponseFuture;
import com.seidor.core.task.executor.interactor.TaskInteractorImpl;
import com.seidor.core.utils.scheduler.UiScheduler;

import java.util.concurrent.Callable;

public class UsuarioDetalleFragmentInteractorImpl extends TaskInteractorImpl implements UsuarioDetalleFragmentInteractor {

    private final UsuarioDetalleFragmentRepository usuarioDetalleFragmentRepository;

    public UsuarioDetalleFragmentInteractorImpl(UiScheduler scheduler, UsuarioDetalleFragmentRepository usuarioDetalleFragmentRepository) {
        super(scheduler);
        this.usuarioDetalleFragmentRepository = usuarioDetalleFragmentRepository;
    }

    @Override
    public ResponseFuture<Usuario> getUsuario(final String idUsuario) {
        return prepare(new Callable<Usuario>() {
            @Override
            public Usuario call() throws Exception {
                return usuarioDetalleFragmentRepository.getUserById(idUsuario);
            }
        });
    }
}
