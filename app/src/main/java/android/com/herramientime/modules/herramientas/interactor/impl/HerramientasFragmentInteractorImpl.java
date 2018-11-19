package android.com.herramientime.modules.herramientas.interactor.impl;

import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.interactor.HerramientasFragmentInteractor;
import android.com.herramientime.modules.herramientas.repository.HerramientasFragmentRepository;
import android.os.AsyncTask;

import java.util.List;

public class HerramientasFragmentInteractorImpl extends AsyncTask<Void, Void, List<Herramienta>> implements HerramientasFragmentInteractor {

    private HerramientasFragmentRepository herramientasFragmentRepository;

    public HerramientasFragmentInteractorImpl(HerramientasFragmentRepository herramientasFragmentRepository) {
        this.herramientasFragmentRepository = herramientasFragmentRepository;
    }

    @Override
    protected List<Herramienta> doInBackground(Void... voids) {
        return null;
    }
}
