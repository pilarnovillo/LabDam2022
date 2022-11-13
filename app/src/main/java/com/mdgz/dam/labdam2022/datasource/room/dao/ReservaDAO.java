package com.mdgz.dam.labdam2022.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.datasource.room.entities.ReservaEntity;

import java.util.UUID;

@Dao
public interface ReservaDAO {

    @Insert()
    void insertar(ReservaEntity reserva);

    @Query("SELECT * FROM reservaentity")
    ReservaEntity[] recuperarReservas();

    @Query("SELECT * FROM reservaentity where usuarioID=:usuarioID")
    ReservaEntity recuperarReservas(UUID usuarioID);
}
