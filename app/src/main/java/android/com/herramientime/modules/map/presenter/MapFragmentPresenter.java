package android.com.herramientime.modules.map.presenter;

import android.com.herramientime.core.presenter.MvpFragmentPresenter;

import com.google.android.gms.maps.model.LatLngBounds;

public interface MapFragmentPresenter extends MvpFragmentPresenter {

    void onCameraPositionChanged(double latitude, double longitude);

    void onCameraBoundsChanged(LatLngBounds latLngBounds);
}
