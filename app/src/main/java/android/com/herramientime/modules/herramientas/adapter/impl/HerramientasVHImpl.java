package android.com.herramientime.modules.herramientas.adapter.impl;

import android.com.herramientime.R;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVH;
import android.com.herramientime.modules.herramientas.adapter.HerramientasVHListener;
import android.com.herramientime.modules.herramientas.entities.Herramienta;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HerramientasVHImpl<DATA extends Herramienta> extends RecyclerView.ViewHolder implements HerramientasVH<DATA>, View.OnClickListener {

    private TextView textViewNombreHerramienta;
    private TextView textViewPrecioExperiencia;
    private ImageView imageViewReservada;
    private ImageView imageViewHerramienta;
    private CardView carViewContainer;
    private Context context;
    private HerramientasVHListener listener;

    public HerramientasVHImpl(View itemView, Context context, HerramientasVHListener listener) {
        super(itemView);
        textViewNombreHerramienta = itemView.findViewById(R.id.textViewNombreHerramienta);
        textViewPrecioExperiencia = itemView.findViewById(R.id.textViewPrecioExperiencia);
        imageViewReservada = itemView.findViewById(R.id.imageViewReservada);
        imageViewHerramienta = itemView.findViewById(R.id.imageViewHerramienta);
        carViewContainer = itemView.findViewById(R.id.carViewContainer);
        carViewContainer.setOnClickListener(this);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void setNombreHerramienta(String text) {
        textViewNombreHerramienta.setText(text);
    }

    @Override
    public void setPrecioExperiencia(String text) {
        textViewPrecioExperiencia.setText(text);
    }

    @Override
    public void setReservadaVisibility(boolean visibility) {
        imageViewReservada.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setImagenHerramienta(String urlImagen) {

        Glide.with(context)
                .load(urlImagen)
                .into(imageViewHerramienta);
    }

    //region OnClickListener

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.carViewContainer:
                listener.onClickItem(getAdapterPosition());
                break;
        }
    }

    //endregion  OnClickListener
}
