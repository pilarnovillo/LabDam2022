package com.mdgz.dam.labdam2022;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentResultadoBusquedaBinding;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultadoBusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadoBusquedaFragment extends Fragment {

    private FragmentResultadoBusquedaBinding binding;
    private ListadoAlojamientoViewModel viewmodel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadoBusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadoBusquedaFragment newInstance(String param1, String param2) {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentResultadoBusquedaBinding.inflate(inflater);
        View view = binding.getRoot();

//        AlojamientoRepository daoSeries = new AlojamientoRepository();
//        RecyclerView recyclerView = binding.recyclerResultadoBusqueda;
//        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        ResultadoBusquedaRecyclerAdapter mAdapter = new ResultadoBusquedaRecyclerAdapter(daoSeries.listaCiudades(getContext()));
//        recyclerView.setAdapter(mAdapter);


        binding.buttonNuevaBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Navigation.findNavController(view1).navigate(R.id.action_resultadoBusquedaFragment_to_busquedaFragment);
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewmodel = new ViewModelProvider(this, new ListadoAlojamientoViewModelFactory(getContext())).get(
                ListadoAlojamientoViewModel.class);
        viewmodel.alojamientoCollection.observe(getViewLifecycleOwner(), alojamientos -> {
            RecyclerView recyclerView = binding.recyclerResultadoBusqueda;
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            ResultadoBusquedaRecyclerAdapter mAdapter = new ResultadoBusquedaRecyclerAdapter(alojamientos);
            recyclerView.setAdapter(mAdapter);
        });

        viewmodel.error.observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Snackbar.make(view, "Algo salio mal: " + error, LENGTH_LONG).show();
                Log.e("ERROR DATA", "Algo salio mal", error);
            }
        });

        viewmodel.recuperarAlojamientos();


    }
}