package com.mdgz.dam.labdam2022.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;
import java.util.UUID;

@Entity(primaryKeys = {"alojamientoID","usuarioID","fechaIngreso","fechaSalida"})
public class ReservaEntity {

    @NonNull
    UUID alojamientoID;
    @NonNull
    UUID usuarioID;
    @NonNull
    Date fechaIngreso;
    @NonNull
    Date fechaSalida;



    public ReservaEntity(@NonNull UUID alojamientoID, @NonNull UUID usuarioID, @NonNull Date fechaIngreso, @NonNull Date fechaSalida) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    @NonNull
    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(@NonNull UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    @NonNull
    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(@NonNull UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    @NonNull
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(@NonNull Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @NonNull
    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(@NonNull Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
