package com.mdgz.dam.labdam2022.datasource.room.mapper;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.datasource.room.entities.DepartamentoEntity;

import java.util.UUID;

public final class DepartamentoMapper {
    private DepartamentoMapper() {
    }

    public static DepartamentoEntity toEntity(final Departamento departamento, final UUID alojamientoId) {
        return new DepartamentoEntity(
            departamento.getId() == null ? UUID.randomUUID() : departamento.getId(),
            departamento.getTieneWifi(),
            departamento.getCostoLimpieza(),
            departamento.getCantidadHabitaciones(),
            alojamientoId
        );
    }

    public static Departamento fromEntity(DepartamentoEntity departamento, AlojamientoEntity alojamiento) {
        if(departamento.getId()==null){
            Log.d("asd","asd");
        }
        return new Departamento(
            departamento.getId(),
            alojamiento.getTitulo(),
            alojamiento.getDescripcion(),
            alojamiento.getCapacidad(),
            alojamiento.getPrecioBase(),
            departamento.getTieneWifi(),
            departamento.getCostoLimpieza(),
            departamento.getCantidadHabitaciones(),
            null // TODO: Agregar ubicaciones
        );
    }
}
