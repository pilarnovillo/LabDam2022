package com.mdgz.dam.labdam2022.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.datasource.ReservaDataSource;
import com.mdgz.dam.labdam2022.datasource.room.dao.ReservaDAO;
import com.mdgz.dam.labdam2022.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.datasource.room.entities.ReservaEntity;
import com.mdgz.dam.labdam2022.datasource.room.mapper.ReservaMapper;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {

    private final ReservaDAO reservaDAO;

    public ReservaRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public ReservaRoomDataSource(final AppDataBase db) {
        reservaDAO = db.reservaDAO();
    }


    @Override
    public void guardarReserva(Reserva reserva, OnResult<Reserva> callback) {
        try {
            final ReservaEntity re = ReservaMapper.toEntity(reserva);
            reservaDAO.insertar(re);
            callback.onSuccess(reserva);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarReservas(OnResult<List<Reserva>> callback) {
        try {
            final ReservaEntity[] reCollection = reservaDAO.recuperarReservas();
            final List<Reserva> reservas = new ArrayList<>();

            for (final ReservaEntity re : reCollection) {
                reservas.add(ReservaMapper.fromEntity(re));
            }
            callback.onSuccess(reservas);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}
