package com.mdgz.dam.labdam2022.datasource;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;

public interface FavoritoDataSource {

    void guardarFavorito(Favorito favorito, OnResult<Favorito> callback);


    void recuperarFavoritos(OnResult<List<Favorito>> callback);

    void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback);



}
