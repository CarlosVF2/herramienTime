package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.entities.HerramientaDetalleFragmentPresenterStatus;
import android.com.herramientime.modules.herramientas.presenter.HerramientaDetalleFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.os.Bundle;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientaDetalleFragmentPresenterImpl<FRAGMENT extends HerramientaDetalleFragment> extends MvpFragmentPresenterImpl<FRAGMENT>
        implements HerramientaDetalleFragmentPresenter {

    private final String PARAM__ID_HERRAM = "PARAM__ID_HERRAM";

    private HerramientaDetalleFragmentPresenterStatus presenterStatus = new HerramientaDetalleFragmentPresenterStatus();

    public static void newHerramientaDetalleFragmentPresenterInstance(Bundle bundle, String id) {
        HerramientaDetalleFragmentPresenterImpl presenter = new HerramientaDetalleFragmentPresenterImpl();
        presenter.setupDependencies(bundle, id);
    }

    private void setupDependencies(Bundle bundle, String id){
        setArguments(bundle);
        bundle.putString(PARAM__ID_HERRAM, id);
    }

    @Override
    public void onViewBinded() {

    }

    @Override
    public void onDataLoaded() {

    }

    @Override
    public boolean isLoadingFinish() {
        return false;
    }
}
