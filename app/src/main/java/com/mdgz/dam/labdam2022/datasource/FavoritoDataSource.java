package com.mdgz.dam.labdam2022.datasource;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

public interface FavoritoDataSource {

    void guardarFavorito(Favorito favorito, OnResult<Favorito> callback);


    void recuperarFavoritos(OnResult<List<Favorito>> callback);



}
