package android.com.herramientime.modules.domain.view.impl;

import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpActivityImpl;
import android.com.herramientime.modules.domain.presenter.MainActivityPresenter;
import android.com.herramientime.modules.domain.view.MainActivity;
import android.com.herramientime.modules.experiencias.view.ExperienciaDetalleFragment;
import android.com.herramientime.modules.experiencias.view.ExperienciasFragment;
import android.com.herramientime.modules.herramientas.view.HerramientaDetalleFragment;
import android.com.herramientime.modules.herramientas.view.HerramientasFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by carlo on 06/11/2018.
 */

public class MainActivityImpl
        <PRESENTER extends MainActivityPresenter> extends MvpActivityImpl<PRESENTER>
        implements MainActivity, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                0,  /* "open drawer" description */
                0  /* "close drawer" description */
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) {
            // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void setToolbar() {
        // Añadir la Toolbar
        setSupportActionBar(toolbar);
        refreshMenu();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //region Lifecycle core

    @Override
    public void onInitLoading() {
        super.onInitLoading();
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
    }

    @Override
    public void onLoadError(String s) {
        super.onLoadError(s);
    }

    //endregion Lifecycle core


    @Override
    public void onBackPressed() {
        getMvpActivityPresenter().onBackPressed();
    }

    @Override
    public void showMessageExitConfirm() {

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_dialog_atencion))
                .setMessage(getString(R.string.promt_message_exit))
                .setPositiveButton(getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PRESENTER presenter = getMvpActivityPresenter();
                        if (presenter != null) {
                            presenter.exitConfirm();
                        }


                    }
                })
                .setNegativeButton(getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }


    @Override
    public void setNombreUsuarioText(String usuario) {
        if (navigationView != null) {
            TextView textView = navigationView.findViewById(R.id.textViewNombre);
            if (textView != null) {
                textView.setText(usuario);
            }
        }
    }

    @Override
    public void setDatosUsuarioVisibility(boolean visibility) {
        if (navigationView != null) {
            TextView textViewId = navigationView.findViewById(R.id.texviewId);
            if (textViewId != null) {
                textViewId.setVisibility(visibility ? View.VISIBLE : View.GONE);
            }
            TextView textViewNombre = navigationView.findViewById(R.id.textViewNombre);
            if (textViewNombre != null) {
                textViewNombre.setVisibility(visibility ? View.VISIBLE : View.GONE);
            }
        }
    }

    @Override
    public void setIDUsuarioText(String id) {
        if (navigationView != null) {
            TextView textView = navigationView.findViewById(R.id.texviewId);
            if (textView != null) {
                textView.setText(id);
            }
        }
    }

    @Override
    public void setButtonIniciarSesionVisibility(boolean visibility) {
        if (navigationView != null) {
            Button buttonIniciarSesion = navigationView.findViewById(R.id.buttonIniciarSesion);
            if (buttonIniciarSesion != null) {
                buttonIniciarSesion.setVisibility(visibility ? View.VISIBLE : View.GONE);
            }
        }
    }


    public void closeDrawer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        }, 50);

    }

    //region Highlight menuItem Selectedç

    @Override
    public void refreshMenu() {
        if (navigationView != null) {
            mDrawerToggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.mainFragment);
            if (f instanceof HerramientasFragment || f instanceof HerramientaDetalleFragment) {
                MenuItem m = navigationView.getMenu().findItem(R.id.nav_herramientas);
                if (m != null) {
                    m.setChecked(true);
                }
            } else if (f instanceof ExperienciasFragment || f instanceof ExperienciaDetalleFragment) {
                MenuItem m = navigationView.getMenu().findItem(R.id.nav_experiencias);
                if (m != null) {
                    m.setChecked(true);
                }
            }

            Button buttonIniciarSesion = navigationView.findViewById(R.id.buttonIniciarSesion);
            ImageView imageViewLog = navigationView.findViewById(R.id.imageView);
            if (imageViewLog != null) {
                imageViewLog.setOnClickListener(this);
            }
            if (buttonIniciarSesion != null) {
                buttonIniciarSesion.setOnClickListener(this);
            }
        }
    }
    //endregion Highlight menuItem Selected


    //region NavigationItemSelected

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        PRESENTER presenter = getMvpActivityPresenter();
        if (presenter != null) {
            switch (id) {
                case R.id.nav_experiencias:
                    presenter.navigationExperienciasClicked();
                    break;
                //case R.id.nav_help:
                //    presenter.navigationHelpClicked();
                //    break;
                //case R.id.nav_settings:
                //    presenter.navigationSettingsClicked();
                //    break;
                case R.id.nav_herramientas:
                    presenter.navigationHerramientasClicked();
                    break;
            }
        }
        return false;
    }

    //endregion NavigationItemSelected

    //region OnClickListener

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonIniciarSesion:
                getMvpActivityPresenter().onClickIniciarSesion();
                break;
            case R.id.imageView:
                getMvpActivityPresenter().onClickImageUser();
                break;
        }
    }

    //endregion OnClickListener

}
