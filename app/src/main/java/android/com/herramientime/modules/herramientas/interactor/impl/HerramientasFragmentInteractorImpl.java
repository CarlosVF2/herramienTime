package android.com.herramientime.modules.herramientas.interactor.impl;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;

import java.util.List;

public class HerramientasFragmentInteractorImpl implements HerramientasFragmentInteractor {

    private HerramientasFragmentRepository herramientasFragmentRepository;

    public HerramientasFragmentInteractorImpl(HerramientasFragmentRepository herramientasFragmentRepository) {
        this.herramientasFragmentRepository = herramientasFragmentRepository;
    }

    @Override
    public List<Herramienta> getHerramientas() {
        return herramientasFragmentRepository.getHerramientas();
    }
}
