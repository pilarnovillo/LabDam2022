package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.datasource.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Aca decimos que el repository implementa el ds para de forma
// facil nos exponga todos los metodos pero no es necesario y podría
// no ser lo que buscamos ( ej: exponer menos metodos o componer metodos del o los datasource)
public class AlojamientoRepository implements AlojamientoDataSource {
    private final AlojamientoDataSource ds;

    public AlojamientoRepository(final AlojamientoDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarHabitacion(final Habitacion habitacion, final OnResult<Habitacion> callback) {
        ds.guardarHabitacion(habitacion, callback);
    }

    @Override
    public void guardarDepartamento(final Departamento departamento, final OnResult<Departamento> callback) {
        ds.guardarDepartamento(departamento, callback);
    }

    @Override
    public void recuperarHabitaciones(final OnResult<List<Habitacion>> callback) {
        ds.recuperarHabitaciones(callback);
    }

    @Override
    public void recuperarDepartamentos(final OnResult<List<Departamento>> callback) {
        ds.recuperarDepartamentos(callback);
    }

    @Override
    public void recuperarAlojaientos(final OnResult<List<Alojamiento>> callback) {
        ds.recuperarAlojaientos(callback);
    }
}