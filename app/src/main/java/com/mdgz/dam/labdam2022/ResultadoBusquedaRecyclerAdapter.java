package com.mdgz.dam.labdam2022;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.repo.FavoritoRepository;

import java.util.List;
import java.util.UUID;

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
        Parcelable alojamientoSeleccionado;
        UUID alojamientoId;

        private RowItemBinding binding;

        public ResultadoBusquedaViewHolder(RowItemBinding binding){
            super(binding.getRoot());
            // TODO hardcodeado como se obtiene de la app?
            UUID user_id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

            this.binding = binding;
            card = this.binding.cardAlojamiento;
            tipoAlojamiento = this.binding.textViewTipoAlojamiento;
            costo = this.binding.textViewCosto;
            ubicacion = this.binding.textViewUbicacion;
            titulo = this.binding.textViewTitulo;
            btnVerDetalle = this.binding.buttonVerDetalle;
            btnFavorito = this.binding.imageButtonFavorito;

            FavoritoRepository favoritoRepository = FavoritoRepositoryFactory.create(titulo.getContext());


            btnVerDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("position", binding.buttonVerDetalle.getTag().toString());
                    bundle.putString("tipoAlojamiento", tipoAlojamiento.getText().toString());
                    bundle.putParcelable("alojamientoSeleccionado", alojamientoSeleccionado);
                    bundle.putString("alojamientoId", alojamientoId.toString());
                    Navigation.findNavController(view1).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment,bundle);
                }
            });

            OnResult<List<Favorito>> isfavoritoCallback = new OnResult<List<Favorito>>() {
                @Override
                public void onSuccess(List<Favorito> result) {

                    for (Favorito r: result){
                        if (alojamientoId.equals(r.getAlojamientoID())){
                            btnFavorito.setImageResource(R.drawable.favorito);
                            btnFavorito.setSelected(true);
                        }
                    }
                }

                @Override
                public void onError(Throwable exception) {
                    exception.printStackTrace();
                }
            };

            AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.recuperarFavoritos(isfavoritoCallback));

            btnFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) { //TODO aca va la logica de buscar si pertenece a la lista de favoritos
                    if (!btnFavorito.isSelected()){

                        OnResult<Favorito> guardarfavoritoCallback = new OnResult<Favorito>() {
                            @Override
                            public void onSuccess(Favorito result) {
                                btnFavorito.setImageResource(R.drawable.favorito);
                                btnFavorito.setSelected(true);
                            }

                            @Override
                            public void onError(Throwable exception) {
                                exception.printStackTrace();
                            }
                        };
                        AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.guardarFavorito(new Favorito(alojamientoId, user_id), guardarfavoritoCallback));
                    }
                    else{
                        OnResult<Favorito> eliminarfavoritoCallback = new OnResult<Favorito>() {
                            @Override
                            public void onSuccess(Favorito result) {
                                btnFavorito.setImageResource(R.drawable.favorite_border);
                                btnFavorito.setSelected(false);
                            }

                            @Override
                            public void onError(Throwable exception) {
                                exception.printStackTrace();
                            }
                        };
                        AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.eliminarFavorito(new Favorito(alojamientoId, user_id), eliminarfavoritoCallback));


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

        return new ResultadoBusquedaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultadoBusquedaViewHolder viewHolder, final int position) {

        viewHolder.btnVerDetalle.setTag(position); //TODO what is this for?
        Alojamiento alojamiento = mDataset.get(position);
        viewHolder.alojamientoSeleccionado = (Parcelable) alojamiento;
        viewHolder.alojamientoId = alojamiento.getId();
        viewHolder.costo.setText("Costo: $"+alojamiento.getPrecioBase().toString());
        viewHolder.titulo.setText(alojamiento.getTitulo());
        //reuse as description
        viewHolder.ubicacion.setText(alojamiento.getDescripcion());

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
