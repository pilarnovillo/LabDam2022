package com.mdgz.dam.labdam2022.datasource.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.datasource.FavoritoDataSource;
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.datasource.room.dao.FavoritoDAO;
import com.mdgz.dam.labdam2022.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.datasource.room.entities.FavoritoEntity;
import com.mdgz.dam.labdam2022.datasource.room.mapper.FavoritoMapper;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.ArrayList;
import java.util.List;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private final FavoritoDAO favoritoDAO;

    public FavoritoRoomDataSource(final Context context) {
        this(AppDataBase.getInstance(context));
    }

    public FavoritoRoomDataSource(final AppDataBase db) {
        favoritoDAO = db.favoritoDAO();
    }



    @Override
    public void guardarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            final FavoritoEntity fe = FavoritoMapper.toEntity(favorito);
            favoritoDAO.insertar(fe);
            callback.onSuccess(favorito);
        } catch (final Exception e) {
            callback.onError(e);
        }

    }

    @Override
    public void recuperarFavoritos(OnResult<List<Favorito>> callback) {
        try {
            final FavoritoEntity[] feCollection = favoritoDAO.recuperarFavoritos();
            final List<Favorito> favoritos = new ArrayList<>();

            for (final FavoritoEntity fe : feCollection) {
                favoritos.add(FavoritoMapper.fromEntity(fe));
            }
            callback.onSuccess(favoritos);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void eliminarFavorito(Favorito favorito, OnResult<Favorito> callback) {
        try {
            final FavoritoEntity fe = FavoritoMapper.toEntity(favorito);
            favoritoDAO.eliminar(fe);
            callback.onSuccess(favorito);
        } catch (final Exception e) {
            callback.onError(e);
        }
    }
}
