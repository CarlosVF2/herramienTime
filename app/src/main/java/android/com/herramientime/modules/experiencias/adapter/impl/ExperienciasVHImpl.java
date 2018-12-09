package android.com.herramientime.modules.experiencias.adapter.impl;

import android.com.herramientime.R;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVH;
import android.com.herramientime.modules.experiencias.adapter.ExperienciasVHListener;
import android.com.herramientime.modules.experiencias.entities.Experiencia;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ExperienciasVHImpl<DATA extends Experiencia> extends RecyclerView.ViewHolder implements ExperienciasVH<DATA>, View.OnClickListener {

    private TextView textViewNombreExperiencia;
    private TextView textViewPrecioExperiencia;
    private ImageView imageViewExperiencia;
    private CardView carViewContainer;
    private Context context;
    private ExperienciasVHListener listener;

    public ExperienciasVHImpl(View itemView, Context context, ExperienciasVHListener listener) {
        super(itemView);
        textViewNombreExperiencia = itemView.findViewById(R.id.textViewNombreExperiencia);
        textViewPrecioExperiencia = itemView.findViewById(R.id.textViewPrecioExperiencia);
        imageViewExperiencia = itemView.findViewById(R.id.imageViewExperiencia);
        carViewContainer = itemView.findViewById(R.id.carViewContainer);
        carViewContainer.setOnClickListener(this);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void setDescripcionExperiencia(String text) {
        textViewNombreExperiencia.setText(text);
    }

    @Override
    public void setPrecioHoraExperiencia(String text) {
        textViewPrecioExperiencia.setText(text);
    }

    @Override
    public void setImagenExperiencia(String urlImagen) {
        Glide.with(context)
                .load(urlImagen)
                .into(imageViewExperiencia);
    }

    //region OnClickListener

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.carViewContainer:
                listener.onClickItem(getAdapterPosition());
                break;
        }
    }

    //endregion  OnClickListener
}
