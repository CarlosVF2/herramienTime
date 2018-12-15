package android.com.herramientime.modules.map.repository.impl;

import android.com.herramientime.domain.processor.ProcessorUsuario;
import android.com.herramientime.modules.domain.entities.LocalException;
import android.com.herramientime.modules.map.entities.MapType;
import android.com.herramientime.modules.map.entities.Point;
import android.com.herramientime.modules.map.repository.MapFragmentRepository;
import android.com.herramientime.modules.usuarios.entities.Usuario;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.InternetException;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentRepositoryImpl implements MapFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorUsuario processorUsuario;

    public MapFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorUsuario processorUsuario) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorUsuario = processorUsuario;
    }


    @Override
    public List<Point> startLoadData(MapType mapType, LatLngBounds currentCameraBounds) throws LocalException, InternetException {
        List<Usuario> usuarios = processorUsuario.convertFrom(restApiServiceHelper.getUsuarios());
        List<Point> points = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (!TextUtils.isEmpty(usuario.getCoordenadaX()) && !TextUtils.isEmpty(usuario.getCoordenadaY())) {
                points.add(new Point(Double.parseDouble(usuario.getCoordenadaX()), Double.parseDouble(usuario.getCoordenadaY()))
                        .setId(usuario.getId())
                        .setTitle(usuario.getNombre()));
            }
        }
        return points;
    }
}
