package com.mdgz.dam.labdam2022;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.databinding.RowItemBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;

public class ResultadoBusquedaRecyclerAdapter extends RecyclerView.Adapter<ResultadoBusquedaRecyclerAdapter.ResultadoBusquedaViewHolder> {

    private List<Alojamiento> mDataset;

    public static class ResultadoBusquedaViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView titulo;
        TextView tipoAlojamiento;
        TextView costo;
        TextView ubicacion;
        Button  btnVerDetalle;
        ImageButton btnFavorito;

        private RowItemBinding binding;

        public ResultadoBusquedaViewHolder(RowItemBinding binding){
            super(binding.getRoot());

            this.binding = binding;
            card = this.binding.cardAlojamiento;
            tipoAlojamiento = this.binding.textViewTipoAlojamiento;
            costo = this.binding.textViewCosto;
            ubicacion = this.binding.textViewUbicacion;
            titulo = this.binding.textViewTitulo;
            btnVerDetalle = this.binding.buttonVerDetalle;
            btnFavorito = this.binding.imageButtonFavorito;

            btnVerDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("position", binding.buttonVerDetalle.getTag().toString());
                    bundle.putString("tipoAlojamiento", tipoAlojamiento.getText().toString());
                    Navigation.findNavController(view1).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment,bundle);
                }
            });

            btnFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) { //TODO aca va la logica de buscar si pertenece a la lista de favoritos
                    if (btnFavorito.isEnabled()){
                        btnFavorito.setImageResource(R.drawable.favorito);
                    }
                }
            });
        }
    }

    public ResultadoBusquedaRecyclerAdapter(List<Alojamiento> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ResultadoBusquedaRecyclerAdapter.ResultadoBusquedaViewHolder onCreateViewHolder(ViewGroup parent, int tipo) {
        RowItemBinding binding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()));

//        return new ViewHolder(binding);

//        View v = LayoutInflater.from(prn.getContext())
//                .inflate(R.layout.row_item, prn, false);

        return new ResultadoBusquedaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultadoBusquedaViewHolder viewHolder, final int position) {

        viewHolder.btnVerDetalle.setTag(position); //TODO what is this for?
        Alojamiento alojamiento = mDataset.get(position);
        viewHolder.costo.setText("Costo: $"+alojamiento.costoDia().toString());
        viewHolder.titulo.setText(alojamiento.getTitulo());
        Ubicacion ubicacionAlojamiento = alojamiento.getUbicacion();
        viewHolder.ubicacion.setText(ubicacionAlojamiento.getCalle()+" "+ubicacionAlojamiento.getNumero()+", "+ubicacionAlojamiento.getCiudad().getNombre());
        if (alojamiento instanceof Departamento){
            Departamento departamento = (Departamento) alojamiento;
            viewHolder.tipoAlojamiento.setText("Departamento");
        }else if (alojamiento instanceof Habitacion){
            Habitacion habitacion = (Habitacion) alojamiento;
            viewHolder.tipoAlojamiento.setText("Habitacion");
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
