package android.com.herramientime.modules.herramientas.repository.impl;

import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.domain.entities.Moneda;
import android.com.herramientime.modules.domain.entities.UsuarioException;
import android.com.herramientime.modules.domain.repository.CategoriasRepository;
import android.com.herramientime.modules.domain.repository.MainActivityRepository;
import android.com.herramientime.modules.domain.repository.MonedasRepository;
import android.com.herramientime.modules.herramientas.entities.AlquilerHerramienta;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.herramientas.repository.AlquilerHerramientaFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.com.rest.utils.Utilidades;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AlquilerHerramientaFragmentRepositoryImpl implements AlquilerHerramientaFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorHerramienta processorHerramienta;
    private final CategoriasRepository categoriasRepository;
    private final MonedasRepository monedasRepository;
    private final MainActivityRepository mainActivityRepository;
    private final Context context;

    public AlquilerHerramientaFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorHerramienta processorHerramienta, CategoriasRepository categoriasRepository, MonedasRepository monedasRepository, MainActivityRepository mainActivityRepository, Context context) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorHerramienta = processorHerramienta;
        this.categoriasRepository = categoriasRepository;
        this.monedasRepository = monedasRepository;
        this.mainActivityRepository = mainActivityRepository;
        this.context = context;
    }

    @Override
    public Herramienta saveHerramienta(AlquilerHerramienta alquilerHerramienta) throws InternetException, LocalException, UsuarioException {
        checkIfAllFieldsAreFill(alquilerHerramienta);
        List<HerramientaRest> herramientaRests = restApiServiceHelper.getHerramientas();
        HerramientaRest herramientaRest = new HerramientaRest();
        herramientaRest.setReservada("0");
        Usuario usuario = mainActivityRepository.getLoggedUser();
        if (usuario == null) {
            throw new LocalException("No se puede obtener el usuario");
        }
        herramientaRest.setIdUsuario(usuario.getId());
        herramientaRest.setNombreUsuario(usuario.getNombre());
        String id = getLastId(herramientaRests.get(herramientaRests.size() - 1).getId());
        herramientaRest.setId(id);
        herramientaRest.setPrecio(alquilerHerramienta.getPrecioTexto());
        herramientaRest.setDescripcion(alquilerHerramienta.getTitulo());
        herramientaRest.setResumen(herramientaRest.getResumen());
        herramientaRest.setMoneda(alquilerHerramienta.getMoneda().getMoneda());
        herramientaRest.setSimboloMoneda(alquilerHerramienta.getMoneda().getSimbolo());
        herramientaRest.setCategoria(alquilerHerramienta.getCategoria().getId());
        herramientaRests.add(herramientaRest);
        if (!TextUtils.isEmpty(alquilerHerramienta.getPathPhoto())) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            storageReference.putFile(Uri.parse(alquilerHerramienta.getPathPhoto())).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String s = "";
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String s = "";
                }
            });
        }
        restApiServiceHelper.postHerramienta(herramientaRests);
        return processorHerramienta.convertFrom(herramientaRest);
    }

    @Override
    public List<Categoria> getCategorias() throws InternetException {
        return categoriasRepository.getCategorias();
    }

    @Override
    public List<Moneda> getMonedas() throws InternetException {
        return monedasRepository.getMonedas();
    }

    @Override
    public String getPathUriPhoto() throws IOException, LocalException, UsuarioException {
        Usuario usuario = mainActivityRepository.getLoggedUser();
        File photoFile = Utilidades.takePhoto(context, usuario.getId());
        Uri uri = Uri.fromFile(photoFile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //android 7 Ó superior
            String fileProvider = context.getPackageName() + ".fileprovider";
            uri = FileProvider.getUriForFile(context, fileProvider, photoFile);
        }
        return uri.toString();
    }

    private String getLastId(String id) {
        int index = Integer.valueOf(id);
        index = index + 1;
        return String.format("%05d", index);
    }

    private void checkIfAllFieldsAreFill(AlquilerHerramienta alquilerHerramienta) throws InternetException {
        if (TextUtils.isEmpty(alquilerHerramienta.getDescripcion())) {
            throw new InternetException("Es necesario rellenar la descripcion");
        } else if (TextUtils.isEmpty(alquilerHerramienta.getPrecioTexto())) {
            throw new InternetException("Es necesario rellenar el precio");
        } else if (TextUtils.isEmpty(alquilerHerramienta.getTitulo())) {
            throw new InternetException("Es necesario rellenar el titulo");
        }
    }
}
