package android.com.herramientime.modules.map.injection;

public class MapFragmentComponent {
    private MapFragmentModule mapFragmentModule;

    public MapFragmentComponent(MapFragmentModule mapFragmentModule) {
        this.mapFragmentModule = mapFragmentModule;
    }

    public MapFragmentModule getMapFragmentModule() {
        return mapFragmentModule;
    }
}
