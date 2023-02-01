package com.mdgz.dam.labdam2022;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldNamingConvention")
public class ListadoAlojamientoViewModel extends ViewModel implements OnResult<List<Alojamiento>> {
    final AlojamientoRepository alojamientoRepository;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    LiveData<Boolean> loading = _loading;
    private final MutableLiveData<List<Alojamiento>> _alojamientoCollection = new MutableLiveData<>(new ArrayList<>());
    LiveData<List<Alojamiento>> alojamientoCollection = _alojamientoCollection;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);
    LiveData<Throwable> error = _error;

    public ListadoAlojamientoViewModel(
        final AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
    }

    public void recuperarAlojamientos() {
        new Thread(() -> {
            _loading.postValue(true);
            alojamientoRepository.recuperarAlojaientos(ListadoAlojamientoViewModel.this);
        }).start();
    }

    @Override
    public void onSuccess(final List<Alojamiento> result) {
        _loading.postValue(false);
        _alojamientoCollection.postValue(result);
    }

    @Override
    public void onError(final Throwable exception) {
        _loading.postValue(false);
        _error.postValue(exception);
    }
}
