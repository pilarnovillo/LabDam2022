package com.mdgz.dam.labdam2022.datasource;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public interface ReservaDataSource {

    void guardarReserva(Reserva reserva, OnResult<Reserva> callback);


    void recuperarReservas(OnResult<List<Reserva>> callback);
}
