package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Criterios;
import com.mdgz.dam.labdam2022.model.LogsBusqueda;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;
import com.mdgz.dam.labdam2022.repo.LogsBusquedaRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentBusquedaBinding binding;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentBusquedaBinding.inflate(getLayoutInflater());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBusquedaBinding.inflate(inflater);
        View view = binding.getRoot();

        ArrayAdapter<Ciudad> adapter= new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,CiudadRepository._CIUDADES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCiudad.setAdapter(adapter);


        binding.buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.mdgz.dam.labdam2022_preferences",0);
                boolean guradarLogsBusqueda = sharedPreferences.getBoolean("check_box_preference_informacion",false);
                System.out.println("guradarLogsBusqueda: "+guradarLogsBusqueda);
                if (guradarLogsBusqueda){
                    Criterios criterios = new Criterios();
                    criterios.setHotel(binding.checkBoxHoteles.isChecked());
                    criterios.setDepartamento(binding.checkBoxDepartamentos.isChecked());
                    criterios.setCantPersonas(binding.editTextNumberCantidadDePersonas.getText().toString());
                    criterios.setWifi(binding.checkBoxWifi.isChecked());
                    criterios.setPrecioMin(binding.editTextNumberDecimalPrecioMinimo.getText().toString());
                    criterios.setPrecioMax(binding.editTextNumberDecimalPrecioMaximo.getText().toString());
                    criterios.setCiudad(binding.spinnerCiudad.toString());

                    LogsBusqueda logsBusqueda = new LogsBusqueda();
                    logsBusqueda.setTimestamp(String.valueOf(new Date()));
                    logsBusqueda.setCriteriosBusqueda(criterios.toString());
                    logsBusqueda.setCantResultados(4); //TODO como calcular esto?
                    logsBusqueda.setTiempoBusqueda("1 second"); //TODO como calcular esto?

                    LogsBusquedaRepository logsBusquedaRepository = new LogsBusquedaRepository(getContext());
                    logsBusquedaRepository.agregar(logsBusqueda);
                }

                Navigation.findNavController(view1).navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
            }
        });

        binding.buttonResetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                binding.checkBoxHoteles.setChecked(false);
                binding.checkBoxDepartamentos.setChecked(false);
                binding.editTextNumberCantidadDePersonas.getText().clear();
                binding.checkBoxWifi.setChecked(false);
                binding.editTextNumberDecimalPrecioMinimo.getText().clear();
                binding.editTextNumberDecimalPrecioMaximo.getText().clear();
                binding.spinnerCiudad.setSelection(0);

            }
        });


        return view;
    }

}