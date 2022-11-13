package com.mdgz.dam.labdam2022.datasource.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.datasource.room.entities.FavoritoEntity;

import java.util.UUID;

@Dao
public interface FavoritoDAO {
    @Insert()
    public void insertar(FavoritoEntity favorito);

    @Query("SELECT * FROM favoritoentity")
    FavoritoEntity[] recuperarFavoritos();

    @Query("SELECT * FROM favoritoentity where usuarioID=:usuarioID")
    FavoritoEntity recuperarFavoritos(UUID usuarioID);
}
