package android.com.herramientime.injection.impl;


import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.modules.domain.presenter.impl.MainActivityPresenterImpl;
import android.content.Intent;

/**
 * Created by carlos 06/11/2018.
 */
public class PresenterFactoryImpl implements PresenterFactory {


    @Override
    public void setupMainActivityIntent(Intent intent) {
        MainActivityPresenterImpl.newMainActivityPresenterInstance(intent);
    }
}
