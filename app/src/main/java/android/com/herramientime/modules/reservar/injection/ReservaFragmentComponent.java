package android.com.herramientime.modules.reservar.injection;

/**
 * Created by carlo on 06/11/2018.
 */

public class ReservaFragmentComponent {

    private ReservaFragmentModule reservaFragmentModule;

    public ReservaFragmentComponent(ReservaFragmentModule reservaFragmentModule) {
        this.reservaFragmentModule = reservaFragmentModule;
    }

    public ReservaFragmentModule getReservaFragmentModule() {
        return reservaFragmentModule;
    }

}
