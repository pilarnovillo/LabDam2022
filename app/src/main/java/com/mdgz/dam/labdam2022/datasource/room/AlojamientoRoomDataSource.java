package com.mdgz.dam.labdam2022.datasource.room;

import android.content.Context;


import com.mdgz.dam.labdam2022.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.datasource.room.dao.AlojamientoDAO;
import com.mdgz.dam.labdam2022.datasource.room.dao.DepartamentoDAO;
import com.mdgz.dam.labdam2022.datasource.room.dao.HabitacionDAO;
import com.mdgz.dam.labdam2022.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.datasource.room.entities.AlojamientoEntity;
import com.mdgz.dam.labdam2022.datasource.room.entities.DepartamentoEntity;
import com.mdgz.dam.labdam2022.datasource.room.entities.HabitacionEntity;
import com.mdgz.dam.labdam2022.datasource.room.mapper.AlojamientoMapper;
import com.mdgz.dam.labdam2022.datasource.room.mapper.DepartamentoMapper;
import com.mdgz.dam.labdam2022.datasource.room.mapper.HabitacionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {
    private final AlojamientoDAO alojamientoDAO;
    private final HabitacionDAO habitacionDAO;
    private final DepartamentoDAO departamentoDAO;

    public AlojamientoRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public AlojamientoRoomDataSource(final AppDataBase db) {
        alojamientoDAO = db.alojamientoDAO();
        habitacionDAO = db.habitacionDAO();
        departamentoDAO = db.departamentoDAO();
    }

    @Override
    public void guardarHabitacion(final Habitacion habitacion, final OnResult<Habitacion> callback) {
        try {
            final AlojamientoEntity ae = AlojamientoMapper.toEntity(habitacion);
            alojamientoDAO.insertar(ae);
            final HabitacionEntity he = HabitacionMapper.toEntity(habitacion, ae.getId());
            habitacionDAO.insertar(he);
            habitacion.setId(he.getId());
            callback.onSuccess(habitacion);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void guardarDepartamento(final Departamento departamento, final OnResult<Departamento> callback) {
        try {
            final AlojamientoEntity ae = AlojamientoMapper.toEntity(departamento);
            alojamientoDAO.insertar(ae);
            final DepartamentoEntity de = DepartamentoMapper.toEntity(departamento, ae.getId());
            departamentoDAO.insertar(de);
            departamento.setId(de.getId());
            callback.onSuccess(departamento);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarHabitaciones(final OnResult<List<Habitacion>> callback) {
        try {
            final HabitacionEntity[] heCollection = habitacionDAO.recuperarHabitaciones();
            final List<Habitacion> habitaciones = new ArrayList<>();
            AlojamientoEntity ae;
            for (final HabitacionEntity he : heCollection) {
                ae = alojamientoDAO.recuperarAlojamientos(he.getAlojamientoId());
                habitaciones.add(HabitacionMapper.fromEntity(he, ae));
            }
            callback.onSuccess(habitaciones);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarDepartamentos(final OnResult<List<Departamento>> callback) {
        try {
            final DepartamentoEntity[] deCollection = departamentoDAO.recuperarDepartamentos();
            final List<Departamento> departamentos = new ArrayList<>();
            AlojamientoEntity ae;
            for (final DepartamentoEntity de : deCollection) {
                ae = alojamientoDAO.recuperarAlojamientos(de.getAlojamientoId());
                departamentos.add(DepartamentoMapper.fromEntity(de, ae));
            }
            callback.onSuccess(departamentos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void recuperarAlojaientos(final OnResult<List<Alojamiento>> callback) {
        try {
            final AlojamientoEntity[] aeCollection = alojamientoDAO.recuperarAlojamientos();
            final List<Alojamiento> alojamientos = new ArrayList<>();
            DepartamentoEntity de;
            HabitacionEntity he;
            for (final AlojamientoEntity ae : aeCollection) {
                if (Objects.equals(ae.getTipo(), AlojamientoEntity.TIPO_DEPARTAMENTO)) {
                    de = departamentoDAO.recuperarDepartamentoConAlojamiento(ae.getId());
                    alojamientos.add(DepartamentoMapper.fromEntity(de, ae));
                } else {
                    he = habitacionDAO.recuperarHabitacionConAlojamiento(ae.getId());
                    alojamientos.add(HabitacionMapper.fromEntity(he, ae));
                }
            }
            callback.onSuccess(alojamientos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}
