package com.mdgz.dam.labdam2022.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Reserva {

    UUID alojamientoID;
    UUID usuarioID;
    Date fechaIngreso;
    Date fechaSalida;

    public Reserva() {
    }

    public Reserva(UUID alojamientoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
