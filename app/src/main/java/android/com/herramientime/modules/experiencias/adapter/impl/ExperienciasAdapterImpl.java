package android.com.herramientime.modules.experiencias.adapter.impl;

import android.com.herramientime.R;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasAdapter;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ExperienciasAdapterImpl<MODEL extends Experiencia> extends RecyclerView.Adapter<ExperienciasVHImpl> implements ExperienciasAdapter<MODEL> {

    private List<MODEL> mAdapterData = new ArrayList<>();
    private final Context context;
    private ExperienciasVHListener listener;

    public ExperienciasAdapterImpl(Context context) {
        this.context = context;
    }

    @Override
    public ExperienciasVHImpl onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_experiencia, parent, false);
        return new ExperienciasVHImpl<>(itemView, context, listener);
    }

    @Override
    public void onBindViewHolder(ExperienciasVHImpl holder, int position) {
        MODEL model = mAdapterData.get(position);
        holder.setDescripcionExperiencia(model.getDescripcion());
        holder.setPrecioHoraExperiencia(model.getPrecioHora() + " " + model.getSimboloMoneda());
        if (!TextUtils.isEmpty(model.getUrlImagen())) {
            holder.setImagenExperiencia(model.getUrlImagen());
        }
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    @Override
    public void setData(List<MODEL> experiencias, ExperienciasVHListener listener) {
        if (this.listener == null) {
            this.listener = listener;
        }
        mAdapterData.clear();
        mAdapterData.addAll(experiencias);
        notifyDataSetChanged();
    }
}
