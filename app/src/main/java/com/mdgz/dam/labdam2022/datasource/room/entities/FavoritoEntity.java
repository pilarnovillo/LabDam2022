package com.mdgz.dam.labdam2022.datasource.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.UUID;

@Entity(primaryKeys = {"alojamientoID","usuarioID"})
public class FavoritoEntity {

    @NonNull
    private UUID alojamientoID;
    @NonNull
    private UUID usuarioID;

    public FavoritoEntity(@NonNull UUID alojamientoID, @NonNull UUID usuarioID) {
        this.alojamientoID = alojamientoID;
        this.usuarioID = usuarioID;
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
}
