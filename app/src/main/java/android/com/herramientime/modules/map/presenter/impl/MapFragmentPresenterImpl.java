package android.com.herramientime.modules.map.presenter.impl;

import android.com.herramientime.app.HerramienTimeApp;
import android.com.herramientime.core.presenter.impl.MvpFragmentPresenterImpl;
import android.com.herramientime.modules.map.entities.MapPresenterStatus;
import android.com.herramientime.modules.map.entities.MapType;
import android.com.herramientime.modules.map.entities.Point;
import android.com.herramientime.modules.map.injection.MapFragmentComponent;
import android.com.herramientime.modules.map.interactor.MapFragmentInteractor;
import android.com.herramientime.modules.map.presenter.MapFragmentPresenter;
import android.com.herramientime.modules.map.view.MapFragment;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLngBounds;
import com.seidor.core.di.annotations.Inject;
import com.seidor.core.task.executor.future.OnCompleted;
import com.seidor.core.task.executor.future.OnData;
import com.seidor.core.task.executor.future.OnError;
import com.seidor.core.task.executor.future.ResponseFuture;

import java.util.List;

public class MapFragmentPresenterImpl<FRAGMENT extends MapFragment>
        extends MvpFragmentPresenterImpl<FRAGMENT>
        implements MapFragmentPresenter {

    //dependences
    private MapFragmentInteractor interactor;
    private MapType mapType;
    private MapPresenterStatus presenterStatus = new MapPresenterStatus();

    private static final String TAG = "MapFragmentPresenterImp";
    private ResponseFuture<List<Point>> responsePoints;
    String PARAM__MAP_TYPE = "PARAM__MAP_TYPE";

    //reponses

    public MapFragmentPresenterImpl() {
        super();
    }

    public static void newMapFragmentPresenterInstance(Bundle bundleWrapper) {
        MapFragmentPresenterImpl presenter = new MapFragmentPresenterImpl();
        presenter.setArguments(bundleWrapper);
    }

    @Inject
    public MapFragmentPresenterImpl(MapFragmentComponent mapFragmentComponent) {
        this.interactor = mapFragmentComponent.getMapFragmentModule().getMapFragmentInteractor();
        setupPresenterStatus();
    }

    private void setupPresenterStatus() {
        if (getPresenterStatus() == null) {
            setPresenterStatus(new MapPresenterStatus());
        }
    }

    //@Override
    //public void onRestoreInstanceState(BundleWrapper bundleWrapper) {
    //    getPresenterStatus().restoreInstance(bundleWrapper);
    //}
//
    //@Override
    //public void onSaveInstanceState(BundleWrapper bundleWrapper) {
    //    getPresenterStatus().saveInstance(bundleWrapper);
    //}

    @Override
    public void onViewBinded() {
        if (interactor == null) {
            interactor = HerramienTimeApp.getComponentDependencies().getMapFragmentComponent().getMapFragmentModule().getMapFragmentInteractor();
        }
        onDataLoaded();
    }

    @Override
    public void onViewUnbinded() {
        super.onViewUnbinded();
    }


    @Override
    public void onDataLoaded() {
        FRAGMENT fragment = getMvpFragment();
        if (fragment != null) {
            if (isLoadingFinish()) {
                addMarkers(fragment);
                fragment.onLoaded();
            } else {
                //Show Progress
            }
        }
    }

    private void addMarkers(FRAGMENT fragment) {
        if (!getPresenterStatus().getPuntos().isEmpty()) {
            for (Point point : getPresenterStatus().getPuntos()) {
                fragment.addMarker(point);

            }
        } else {
            fragment.showAlertDialogMessage("No hay clientes en esta región");
        }
    }


    @Override
    public boolean isLoadingFinish() {
        return true;
    }

    public MapFragmentInteractor getInteractor() {
        return interactor;
    }

    public MapType getMapType() {
        return mapType;
    }

    private void startLoadData() {
        if (responsePoints != null) {
            responsePoints.cancel(true);
        }
        responsePoints = getInteractor().startLoadData(getMapType(), getPresenterStatus().getCurrentCameraBounds()).onData(new OnData<List<Point>>() {
            @Override
            public void onData(List<Point> clientes) {
                getPresenterStatus().setPuntos(clientes);
            }
        }).onError(new OnError() {
            @Override
            public void onError(Exception e) {
                getPresenterStatus().setError(e);
            }
        }).onCompleted(new OnCompleted() {
            @Override
            public void onCompleted() {
                onDataLoaded();
            }
        });
    }

    public MapPresenterStatus getPresenterStatus() {
        return presenterStatus;
    }

    public void setPresenterStatus(MapPresenterStatus presenterStatus) {
        this.presenterStatus = presenterStatus;
    }

    @Override
    public void onCameraPositionChanged(double latitude, double longitude) {
        getPresenterStatus().setCurrentCameraPosition(latitude, longitude);
    }

    @Override
    public void onCameraBoundsChanged(LatLngBounds bounds) {
        getPresenterStatus().setCurrentCameraBounds(bounds);
        startLoadData();
    }
}