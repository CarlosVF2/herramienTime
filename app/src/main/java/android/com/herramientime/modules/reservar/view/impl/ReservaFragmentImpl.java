package android.com.herramientime.modules.reservar.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.reservar.presenter.ReservaFragmentPresenter;
import android.com.herramientime.modules.reservar.view.ReservaFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by carlo on 06/11/2018.
 */

public class ReservaFragmentImpl
        <PRESENTER extends ReservaFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements ReservaFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserva, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reserva, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_confirmar:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //region Core LifeCycle

    @Override
    public void onInitLoading() {

    }

    @Override
    public void onLoaded() {

    }

    //endregion Core LifeCycle
}
