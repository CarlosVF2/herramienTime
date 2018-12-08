package android.com.herramientime.domain.processor;

import android.com.herramientime.modules.domain.entities.Categoria;
import android.com.rest.entities.CategoriaRest;

import java.util.ArrayList;
import java.util.List;

public class ProcessorCategoria {

    public List<Categoria> convertFrom(List<CategoriaRest> categoriaRests) {
        List<Categoria> categorias = new ArrayList<>();
        for (CategoriaRest herramientaRest : categoriaRests) {
            categorias.add(convertFrom(herramientaRest));
        }
        return categorias;
    }

    public Categoria convertFrom(CategoriaRest from) {
        Categoria categoria = new Categoria();
        categoria.setDescripcion(from.getDescripcion());
        categoria.setId(from.getId());
        return categoria;
    }


}
