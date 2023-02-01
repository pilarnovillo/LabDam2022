package com.mdgz.dam.labdam2022;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mdgz.dam.labdam2022.factory.AlojamientoRepositoryFactory;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

public class ListadoAlojamientoViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public ListadoAlojamientoViewModelFactory(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListadoAlojamientoViewModel.class)) {
            final AlojamientoRepository repo = AlojamientoRepositoryFactory.create(context);
            //noinspection unchecked
            return (T) new ListadoAlojamientoViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
