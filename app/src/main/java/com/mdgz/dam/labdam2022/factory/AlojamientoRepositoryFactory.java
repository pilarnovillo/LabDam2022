package com.mdgz.dam.labdam2022.factory;

import android.content.Context;

import com.mdgz.dam.labdam2022.datasource.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;


public final class AlojamientoRepositoryFactory {
    private AlojamientoRepositoryFactory() {
    }

    public static AlojamientoRepository create(final Context context) {
        return new AlojamientoRepository(new AlojamientoRoomDataSource(context));
    }
}
