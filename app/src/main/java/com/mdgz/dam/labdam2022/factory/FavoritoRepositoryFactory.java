package com.mdgz.dam.labdam2022.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.datasource.room.FavoritoRoomDataSource;
import com.mdgz.dam.labdam2022.repo.FavoritoRepository;

public class FavoritoRepositoryFactory {

    private FavoritoRepositoryFactory() {
    }

    public static FavoritoRepository create(final Context context) {
        return new FavoritoRepository(new FavoritoRoomDataSource(context));
    }
}
