package com.mdgz.dam.labdam2022;

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
import com.mdgz.dam.labdam2022.databinding.RowItemLogsbusquedaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.LogsBusqueda;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;

public class DetalleLogsRecyclerAdapter extends RecyclerView.Adapter<DetalleLogsRecyclerAdapter.DetalleLogsViewHolder> {

    private List<LogsBusqueda> mDataset;

    public static class DetalleLogsViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView timestamp;
        TextView criterios;
        TextView cantResultados;
        TextView tiempoBusqueda;


        private RowItemLogsbusquedaBinding binding;

        public DetalleLogsViewHolder(RowItemLogsbusquedaBinding binding){
            super(binding.getRoot());

            this.binding = binding;
            card = this.binding.cardLogs;
            criterios = this.binding.textViewCriterios;
            cantResultados = this.binding.textViewCantResultados;
            tiempoBusqueda = this.binding.textViewTiempoBusqueda;
            timestamp = this.binding.textViewTimestamp;

        }
    }

    public DetalleLogsRecyclerAdapter(List<LogsBusqueda> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DetalleLogsRecyclerAdapter.DetalleLogsViewHolder onCreateViewHolder(ViewGroup parent, int tipo) {
        RowItemLogsbusquedaBinding binding = RowItemLogsbusquedaBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new DetalleLogsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DetalleLogsViewHolder viewHolder, final int position) {

        LogsBusqueda logsBusqueda = mDataset.get(position);
        viewHolder.cantResultados.setText(String.valueOf(logsBusqueda.getCantResultados()));
        viewHolder.timestamp.setText(logsBusqueda.getTimestamp());
        viewHolder.tiempoBusqueda.setText(logsBusqueda.getTiempoBusqueda());
        viewHolder.criterios.setText(logsBusqueda.getCriteriosBusqueda());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
