package com.mdgz.dam.labdam2022.datasource.room.mapper;

import com.mdgz.dam.labdam2022.datasource.room.entities.ReservaEntity;
import com.mdgz.dam.labdam2022.model.Reserva;

public class ReservaMapper {
    private ReservaMapper() {
    }

    public static ReservaEntity toEntity(final Reserva reserva) {
        return new ReservaEntity(
                reserva.getAlojamientoID(),
                reserva.getUsuarioID(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }

    public static Reserva fromEntity(ReservaEntity reserva) {

        return new Reserva(
                reserva.getAlojamientoID(),
                reserva.getUsuarioID(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }
}
