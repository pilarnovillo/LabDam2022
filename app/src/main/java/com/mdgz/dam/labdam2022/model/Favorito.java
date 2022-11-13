package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;

import java.util.UUID;
public class Favorito {

    private UUID alojamientoID;
    private UUID usuarioID;

    public Favorito() {
    }

    public Favorito(UUID alojamientoID, UUID usuarioID) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
    }


    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}
