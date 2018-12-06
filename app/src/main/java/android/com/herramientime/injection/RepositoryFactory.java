package android.com.herramientime.injection;

import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientaDetalleFragmentRepository;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;

/**
 * Created by carlos 06/11/2018.
 */

public interface RepositoryFactory {
    //Main
    MainActivityRepository getMainActivityRepository();

    //Harramientas
    HerramientasFragmentRepository getHerramientasFragmentRepository();
    HerramientaDetalleFragmentRepository getHerramientaDetalleFragmentRepository();
}
