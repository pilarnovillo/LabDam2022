package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.datasource.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

public class FavoritoRepository implements FavoritoDataSource {
    private final FavoritoDataSource ds;

    public FavoritoRepository(final FavoritoDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        ds.guardarFavorito(favorito, callback);
    }

    @Override
    public void recuperarFavoritos(OnResult<List<Favorito>> callback) {
        ds.recuperarFavoritos(callback);
    }

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        ds.eliminarFavorito(favorito, callback);
    }
}
