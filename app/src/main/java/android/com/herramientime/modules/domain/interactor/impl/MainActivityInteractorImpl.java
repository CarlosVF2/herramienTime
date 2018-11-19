package android.com.herramientime.modules.domain.interactor.impl;

import android.com.herramientime.modules.domain.interactor.MainActivityInteractor;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;

public class MainActivityInteractorImpl implements MainActivityInteractor {

    private MainActivityRepository mainActivityRepository;

    public MainActivityInteractorImpl(MainActivityRepository mainActivityRepository) {
        this.mainActivityRepository = mainActivityRepository;
    }
}
