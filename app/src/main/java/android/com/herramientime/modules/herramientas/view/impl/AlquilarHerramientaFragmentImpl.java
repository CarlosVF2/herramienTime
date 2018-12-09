package android.com.herramientime.modules.herramientas.view.impl;

import android.app.Activity;
import android.com.herramientime.R;
import android.com.herramientime.core.view.impl.MvpFragmentImpl;
import android.com.herramientime.modules.herramientas.presenter.AlquilerHerramientaFragmentPresenter;
import android.com.herramientime.modules.herramientas.view.AlquilarHerramientaFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by carlo on 06/11/2018.
 */

public class AlquilarHerramientaFragmentImpl
        <PRESENTER extends AlquilerHerramientaFragmentPresenter> extends MvpFragmentImpl<PRESENTER>
        implements AlquilarHerramientaFragment, TextWatcher, AdapterView.OnItemSelectedListener {

    private TextInputLayout textInputLayoutTitulo;
    private TextInputLayout textInputLayoutDescripcion;
    private TextInputLayout textInputLayoutPrecio;
    private ImageView imageViewFoto;
    private Spinner spinnerSimbolos;
    private Spinner spinnerCategoria;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    private Menu menu;
    private ArrayAdapter<String> adapterSpinnerSimbolos;
    private ArrayAdapter<String> adapterSpinnerCategoria;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alquilar_herramienta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponentes(view);
    }

    private void initComponentes(View view) {
        textInputLayoutTitulo = view.findViewById(R.id.textInputLayoutTitulo);
        textInputLayoutDescripcion = view.findViewById(R.id.textInputLayoutDescripcion);
        textInputLayoutPrecio = view.findViewById(R.id.textInputLayoutPrecio);
        imageViewFoto = view.findViewById(R.id.imageViewFoto);
        spinnerSimbolos = view.findViewById(R.id.spinnerSimbolos);
        spinnerSimbolos.setOnItemSelectedListener(this);
        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);
        textInputLayoutTitulo.getEditText().addTextChangedListener(this);
        textInputLayoutDescripcion.getEditText().addTextChangedListener(this);
        textInputLayoutPrecio.getEditText().addTextChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_alquilar_herramienta, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_confirmar:
                getMvpPresenter().onClickConfirmar();
                return true;
            case R.id.action_make_photo:
                getMvpPresenter().onClickMakePhoto();
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

    public void setVisibilityMenuItemConfirmar(boolean visibilitMenuItemConfirmar) {
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.action_confirmar);
            if (menuItem != null) {
                menuItem.setVisible(visibilitMenuItemConfirmar);
            }
        }
    }

    @Override
    public void launchIntentPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public String createPhoto(String id, Bitmap bitmap) {
        //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        return MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, id, null);
    }

    @Override
    public Bitmap getBitmapImage() {
        return imageBitmap;
    }

    @Override
    public void setAdapterSpinnerMoneda(List<String> valuesAdapterMoneda) {
        if (adapterSpinnerSimbolos == null) {
            String[] valores = valuesAdapterMoneda.toArray(new String[valuesAdapterMoneda.size()]);
            adapterSpinnerSimbolos = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, valores);
        }
        spinnerSimbolos.setAdapter(adapterSpinnerSimbolos);
    }

    @Override
    public void setAdapterSpinnerCategoria(List<String> valuesAdapterCategoria) {
        if (adapterSpinnerCategoria == null) {
            String[] valores = valuesAdapterCategoria.toArray(new String[valuesAdapterCategoria.size()]);
            adapterSpinnerCategoria = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, valores);
        }
        spinnerCategoria.setAdapter(adapterSpinnerCategoria);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);
        }
    }

    //region TextWatcher

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        PRESENTER presenter = getMvpPresenter();
        if (presenter != null) {
            if (textInputLayoutTitulo.getEditText().getEditableText() == editable) {
                presenter.setTitulo(editable.toString());
            } else if (textInputLayoutDescripcion.getEditText().getEditableText() == editable) {
                presenter.setDescripcion(editable.toString());
            } else if (textInputLayoutPrecio.getEditText().getEditableText() == editable) {
                presenter.setPrecio(editable.toString());
            }
        }
    }

    //endregion TextWatcher

    //region OnItemSelectedListener

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int id = adapterView.getId();
        switch (id) {
            case R.id.spinnerCategoria:
                getMvpPresenter().onItemCateogiraSelected(i);
                break;
            case R.id.spinnerSimbolos:
                getMvpPresenter().onItemSimbolosSelected(i);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //endregion OnItemSelectedListener

}
