package android.com.herramientime.modules.herramientas.presenter.impl;

import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.modules.herramientas.presenter.HerramientasFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
import android.os.Bundle;

/**
 * Created by carlo on 06/11/2018.
 */

public class HerramientasFragmentPresenterImpl<VIEW extends HerramientasFragment> extends MvpFragmentPresenterImpl<VIEW>
        implements HerramientasFragmentPresenter {

    public static void newHerramientasFragmentPresenterInstance(Bundle bundle) {
        HerramientasFragmentPresenterImpl presenter = new HerramientasFragmentPresenterImpl();
        presenter.setArguments(bundle);
    }

    @Override
    public void onViewBinded() {
        super.onViewBinded();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
