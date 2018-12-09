package android.com.herramientime.modules.reservar.repository.impl;

import android.com.herramientime.domain.processor.ProcessorExperiencia;
import android.com.herramientime.domain.processor.ProcessorHerramienta;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.com.herramientime.modules.reservar.repository.ReservaFragmentRepository;
import android.com.rest.RestApiServiceHelper;
import android.com.rest.entities.ExperienciaRest;
import android.com.rest.entities.HerramientaRest;
import android.com.rest.entities.InternetException;
import android.com.rest.utils.Utilidades;

import java.util.Date;
import java.util.List;

public class ReservaFragmentRepositoryImpl implements ReservaFragmentRepository {

    private final RestApiServiceHelper restApiServiceHelper;
    private final ProcessorHerramienta processorHerramienta;
    private final ProcessorExperiencia processorExperiencia;

    public ReservaFragmentRepositoryImpl(RestApiServiceHelper restApiServiceHelper, ProcessorHerramienta processorHerramienta, ProcessorExperiencia processorExperiencia) {
        this.restApiServiceHelper = restApiServiceHelper;
        this.processorHerramienta = processorHerramienta;
        this.processorExperiencia = processorExperiencia;
    }

    @Override
    public Experiencia getExperienciaById(String idExperiencia) throws InternetException {
        List<ExperienciaRest> experienciaRests = restApiServiceHelper.getExperiencias();
        ExperienciaRest experienciaRest = new ExperienciaRest(idExperiencia);
        int index = experienciaRests.indexOf(experienciaRest);
        if (index > -1) {
            return processorExperiencia.convertFrom(experienciaRests.get(index));
        }
        return null;
    }

    @Override
    public Herramienta getHerramientaById(String idHerramienta) throws InternetException {
        List<HerramientaRest> herramientaRestList = restApiServiceHelper.getHerramientas();
        HerramientaRest herramientaRest = new HerramientaRest(idHerramienta);
        int index = herramientaRestList.indexOf(herramientaRest);
        if (index > -1) {
            return processorHerramienta.convertFrom(herramientaRestList.get(index));
        }
        return null;
    }

    @Override
    public Boolean reservar(Experiencia experiencia, Herramienta herramienta, Date fechaInicial, Date fechaFinal) throws InternetException {
        checkFieldsNotEmpty(fechaInicial, fechaFinal);
        Date fechaReservaIni = null;
        Date fechaReservaFin = null;
        boolean isReserved = false;
        if (experiencia != null) {
            //Vamos a reservar la experiencia por lo que tenemos que ver que las fechas seleccionadas no esta reservado
            fechaReservaIni = experiencia.getFechaInicial();
            fechaReservaFin = experiencia.getFechaFinal();
            isReserved = experiencia.isReservada();
        } else if (herramienta != null) {
            //Vamos a reservar la experiencia por lo que tenemos que ver que las fechas seleccionadas no esta reservado
            fechaReservaIni = herramienta.getFechaInicio();
            fechaReservaFin = herramienta.getFechaFin();
        }
        if (isReserved) {
            //si esta reservado ver si son las mismas fechas
            if (fechaInicial.after(fechaReservaIni) && fechaFinal.before(fechaReservaFin)) {
                throw new InternetException("En estos momentos esta reservado");
            }
        }
        //Se reserva la experiencia o herramienta
        reservate(experiencia, herramienta, fechaInicial, fechaFinal);
        return true;
    }

    private void reservate(Experiencia experiencia, Herramienta herramienta, Date fechaInicial, Date fechaFinal) throws InternetException {
        if (experiencia != null) {
            List<ExperienciaRest> experienciaRestList = restApiServiceHelper.getExperiencias();
            ExperienciaRest experienciaRest = processorExperiencia.convertFrom(experiencia);
            experienciaRest.setFechaFinal(Utilidades.getStringFormatddMMyyyyGuiones(fechaFinal));
            experienciaRest.setFechaInicial(Utilidades.getStringFormatddMMyyyyGuiones(fechaInicial));
            experienciaRest.setReservada("1");
            int index = experienciaRestList.indexOf(experienciaRest);
            if (index > -1) {
                experienciaRestList.set(index, experienciaRest);
            }
            restApiServiceHelper.postExperiencia(experienciaRestList);
        } else if (herramienta != null) {
            List<HerramientaRest> herramientaRestList = restApiServiceHelper.getHerramientas();
            HerramientaRest herramientaRest = processorHerramienta.convertFrom(herramienta);
            herramientaRest.setFechaInicio(Utilidades.getStringFormatddMMyyyyGuiones(fechaFinal));
            herramientaRest.setFechaFin(Utilidades.getStringFormatddMMyyyyGuiones(fechaInicial));
            herramientaRest.setReservada("1");
            int index = herramientaRestList.indexOf(herramientaRest);
            if (index > -1) {
                herramientaRestList.set(index, herramientaRest);
            }
            restApiServiceHelper.postHerramienta(herramientaRestList);
        }
    }

    private void checkFieldsNotEmpty(Date fechaInicial, Date fechaFinal) throws InternetException {
        if (fechaInicial == null) {
            throw new InternetException("Tiene que rellenar la fecha inicial");
        }
        if (fechaFinal == null) {
            throw new InternetException("Tiene que rellenar la fecha final");
        }
    }
}
