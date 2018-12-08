package android.com.rest.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseExperiencia {

    @SerializedName("Experiencia")
    private List<ExperienciaRest> experiencia;

    public void setExperiencia(List<ExperienciaRest> experiencia) {
        this.experiencia = experiencia;
    }

    public List<ExperienciaRest> getExperiencia() {
        return experiencia;
    }

    @Override
    public String toString() {
        return
                "ResponseExperiencia{" +
                        "experiencia = '" + experiencia + '\'' +
                        "}";
    }
}