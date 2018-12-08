package android.com.herramientime.modules.herramientas.adapter.impl;

import android.com.herramientime.R;
import android.com.herramientime.modules.herramientas.adapter.HerramientasAdapter;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HerramientasAdapterImpl<MODEL extends Herramienta> extends RecyclerView.Adapter<HerramientasVHImpl> implements HerramientasAdapter<MODEL> {

    private List<MODEL> mAdapterData = new ArrayList<>();
    private final Context context;
    private HerramientasVHListener listener;

    public HerramientasAdapterImpl(Context context) {
        this.context = context;
    }

    @Override
    public HerramientasVHImpl onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_herramienta, parent, false);
        return new HerramientasVHImpl<>(itemView, context, listener);
    }

    @Override
    public void onBindViewHolder(HerramientasVHImpl holder, int position) {
        MODEL model = mAdapterData.get(position);
        holder.setNombreHerramienta(model.getDescripcion());
        holder.setReservadaVisibility(model.isReservada());
        holder.setPrecioExperiencia(model.getPrecioText() + model.getSimboloMoneda());
        if(!TextUtils.isEmpty(model.getUrlImagen())) {
            holder.setImagenHerramienta(model.getUrlImagen());
        }
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }

    @Override
    public void setData(List<MODEL> herramientas, HerramientasVHListener listener) {
        if(this.listener == null){
            this.listener = listener;
        }
        mAdapterData.clear();
        mAdapterData.addAll(herramientas);
        notifyDataSetChanged();
    }
}
