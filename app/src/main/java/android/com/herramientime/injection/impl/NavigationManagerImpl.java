package android.com.herramientime.injection.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.com.herramientime.R;
import android.com.herramientime.core.view.MvpFragment;
import android.com.herramientime.injection.NavigationManager;
import android.com.herramientime.injection.PresenterFactory;
import android.com.herramientime.injection.ViewFactory;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.experiencias.view.impl.AlquilarExperienciaFragmentImpl;
import android.com.herramientime.modules.experiencias.view.impl.ExperienciaDetalleFragmentImpl;
import android.com.herramientime.modules.experiencias.view.impl.ExperienciasFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.AlquilarHerramientaFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientaDetalleFragmentImpl;
import android.com.herramientime.modules.herramientas.view.impl.HerramientasFragmentImpl;
import android.com.herramientime.modules.login.view.impl.LoginFragmentImpl;
import android.com.herramientime.modules.map.view.impl.MapFragmentImpl;
import android.com.herramientime.modules.reservar.view.impl.ReservaFragmentImpl;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by carlos 06/11/2018.
 */
public class NavigationManagerImpl implements NavigationManager, Application.ActivityLifecycleCallbacks, FragmentManager.OnBackStackChangedListener {

    /**
     * Listener interface para eventos de navegación
     */
    public interface NavigationListener {
        void onBackstackChanged();
    }

    private PresenterFactory presenterFactory;
    private ViewFactory viewFactory;
    private FragmentActivity currentActivity;
    private Context context;
    private NavigationListener navigationListener;

    public NavigationManagerImpl(Application application, ViewFactory viewFactory, PresenterFactory presenterFactory, Context context) {
        this.viewFactory = viewFactory;
        this.presenterFactory = presenterFactory;
        this.context = context;
        application.registerActivityLifecycleCallbacks(this);
    }

    //region ExperienciasFragment
    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(viewFactory.getMainActivityIntent());
        presenterFactory.setupMainActivityIntent(intent);
        context.startActivity(intent);
    }
    //endregion ExperienciasFragment

    //region Herramientas

    @Override
    public void navigateToHerramientas() throws LocalException {
        if (currentActivity != null) {
            navigateToRoot(currentActivity.getSupportFragmentManager());
            Bundle args = new Bundle();
            presenterFactory.setupHerramientasFragmentInstance(args);
            HerramientasFragmentImpl fragment = viewFactory.newHerramientasFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    @Override
    public void navigateToDetalleHerramienta(String idHerramienta) throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupHerramientaDetalleFragmentInstance(args, idHerramienta);
            HerramientaDetalleFragmentImpl fragment = viewFactory.newHerramientaDetalleFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    //endregion Herramientas

    //region Experiencias

    @Override
    public void navigateToExperiencias() throws LocalException {
        if (currentActivity != null) {
            navigateToRoot(currentActivity.getSupportFragmentManager());
            Bundle args = new Bundle();
            presenterFactory.setupExperienciasFragmentInstance(args);
            ExperienciasFragmentImpl fragment = viewFactory.newExperienciasFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    @Override
    public void navigateToDetalleExperiencia(String idHerramienta) throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupExperienciaDetalleFragmentInstance(args, idHerramienta);
            ExperienciaDetalleFragmentImpl fragment = viewFactory.newExperienciaDetalleFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    //endregion Experiencias

    //region reserva

    @Override
    public void navigateToReservaExperiencia(String idExperiencia, MvpFragment currentFragment, int requestCode) throws LocalException {
        navigateToReserva(idExperiencia, "", currentFragment, requestCode);
    }

    @Override
    public void navigateToReservaHerramienta(String idHerramienta, MvpFragment currentFragment, int requestCode) throws LocalException {
        navigateToReserva("", idHerramienta, currentFragment, requestCode);
    }

    private void navigateToReserva(String idExperiencia, String idHerramienta, MvpFragment currentFragment, int requestCode) throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupReservaFragmentInstance(args, idExperiencia, idHerramienta);
            ReservaFragmentImpl fragment = viewFactory.newReservaFragmentInstance();
            fragment.setArguments(args);
            if (currentFragment instanceof MvpFragment) {
                Fragment targetFragment = (Fragment) currentFragment;
                if (openWithTargetFragment(fragment, targetFragment, requestCode)) return;
            }
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    //endregion reserva

    //region Alquiler

    @Override
    public void navigateToAlquilerHerramienta() throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupAlquilerHerramientaFragmentInstance(args);
            AlquilarHerramientaFragmentImpl fragment = viewFactory.newAlquilerHerramientaFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    @Override
    public void navigateToAlquilerExperiencia() throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupAlquilerExperienciaFragmentInstance(args);
            AlquilarExperienciaFragmentImpl fragment = viewFactory.newAlquilerExperienciaFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    //endregion Alquiler

    //region Login

    @Override
    public void navigateToLogin() throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupLoginFragmentInstance(args);
            LoginFragmentImpl fragment = viewFactory.newLoginFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    //endregion Login

    //region Map

    @Override
    public void navigateToMap() throws LocalException {
        if (currentActivity != null) {
            Bundle args = new Bundle();
            presenterFactory.setupMapFragmentInstance(args);
            MapFragmentImpl fragment = viewFactory.newMapFragmentInstance();
            fragment.setArguments(args);
            open(currentActivity.getSupportFragmentManager(), fragment, null);
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }

    }

    //endregion Map

    //region NavigateBack

    @Override
    public void navigateBack() throws LocalException {
        if (currentActivity != null) {
            navigateBack(currentActivity, currentActivity.getSupportFragmentManager());
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    public void navigateBack(@NonNull Activity activity, @NonNull FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            activity.finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

    //endregion NavigateBack

    //region Gestion Fragments / Activity

    /**
     * @return true if the has fragment displayed
     */
    @Override
    public boolean isFragmentAttached() throws LocalException {
        if (currentActivity != null) {
            return isFragmentAttached(currentActivity.getSupportFragmentManager());
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    @Override
    public boolean isRootFragment() throws LocalException {
        if (currentActivity != null) {
            return currentActivity.getSupportFragmentManager().getBackStackEntryCount() == 1;
        } else {
            throw new LocalException(context.getString(R.string.error_activity_not_prepared));
        }
    }

    private void navigateToRoot(@NonNull FragmentManager fragmentManager) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    private boolean isFragmentAttached(FragmentManager fragmentManager) {
        return fragmentManager.getBackStackEntryCount() > 0;
    }

    private void startActivity(Intent intent) {
        if (currentActivity == null) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        } else {
            currentActivity.startActivity(intent);
        }
    }

    private void popEveryFragment(FragmentManager fragmentManager) {
        // Clear all back stack.
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void open(FragmentManager fragmentManager, Fragment fragment, String backStack) {
        Fragment currentFragment = getCurrentFragmentById(fragmentManager);
        if (currentFragment != fragment) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout)
                    .replace(R.id.mainFragment, fragment)
                    .addToBackStack(backStack)
                    .commit();
        }
    }

    private Fragment getCurrentFragmentById(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            return fragmentManager.findFragmentById(R.id.mainFragment);
        }
        return null;
    }

    private boolean openWithTargetFragment(Fragment fragment, Fragment targetFragment, int requestCode) {
        return openWithTargetFragment(fragment, targetFragment, requestCode, false);
    }

    private boolean openWithTargetFragment(Fragment fragment, Fragment targetFragment, int requestCode, boolean dialog) {
        fragment.setTargetFragment(targetFragment, requestCode);
        FragmentManager fragmentManager = getParentManager(targetFragment);
        if (fragmentManager != null) {
            if (dialog) {
                openAsDialog(fragmentManager, fragment, null);
            } else {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout)
                        .add(fragment, null)
                        .commit();
            }
            return true;
        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    private FragmentManager getParentManager(Fragment targetFragment) {
        Fragment parentFragment = targetFragment.getParentFragment();
        if (parentFragment != null) {
            if (parentFragment.getChildFragmentManager().getFragments().contains(targetFragment)) {
                return parentFragment.getChildFragmentManager();
            } else {
                return getParentManager(parentFragment);
            }
        } else if (currentActivity != null) {
            return currentActivity.getSupportFragmentManager();
        } else return null;
    }

    private void openAsDialog(FragmentManager fragmentManager, Fragment fragment, String backStack) {
        Fragment currentFragment = getCurrentFragmentById(fragmentManager);
        if (currentFragment != fragment) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            Fragment prev = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName() + " detail");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(backStack);
            ((DialogFragment) fragment).show(fragmentManager, fragment.getClass().getSimpleName() + "detail");
        }
    }

    //endregion  Gestion Fragments / Activity

    //region setNavigationListener
    @Override
    public void setNavigationListener(NavigationListener navigationListener) {
        this.navigationListener = navigationListener;
    }
    //endregion setNavigationListener

    // region Application.ActivityLifecycleCallbacks

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        currentActivity = (FragmentActivity) activity;
        currentActivity.getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // nothing to do here
        // currentActivity = (FragmentActivity) activity;
        //currentActivity.getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // nothing to do here
        currentActivity = (FragmentActivity) activity;
        currentActivity.getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // nothing to do here
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // nothing to do here
        if (currentActivity == activity) {
            currentActivity.getSupportFragmentManager().removeOnBackStackChangedListener(this);
            currentActivity = null;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        // nothing to do here
        if (currentActivity == activity) {
            currentActivity = null;
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // nothing to do here
    }

    // endregion

    // region OnBackStackChangedListener

    @Override
    public void onBackStackChanged() {
        if (navigationListener != null) {
            navigationListener.onBackstackChanged();
        }
    }

    // endregion
}
