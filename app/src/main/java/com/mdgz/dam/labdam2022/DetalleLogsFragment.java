package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleLogsBinding;
import com.mdgz.dam.labdam2022.model.LogsBusqueda;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.LogsBusquedaRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleLogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleLogsFragment extends Fragment {

    FragmentDetalleLogsBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleLogsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleLogsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleLogsFragment newInstance(String param1, String param2) {
        DetalleLogsFragment fragment = new DetalleLogsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDetalleLogsBinding.inflate(getLayoutInflater());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetalleLogsBinding.inflate(inflater);
        View view = binding.getRoot();
        LogsBusquedaRepository logsBusquedaRepository = new LogsBusquedaRepository(getContext());

//        for (LogsBusqueda logsBusqueda: logsBusquedaRepository.listarTodos()){
//            System.out.println(logsBusqueda.getTimestamp());
//            CharSequence text= binding.textViewDetalleLog.getText();
//            binding.textViewDetalleLog.setText(text + logsBusqueda.getTimestamp());
//        }

        LogsBusquedaRepository daoSeries = new LogsBusquedaRepository(getContext());
        RecyclerView recyclerView = binding.recyclerDetalleLogs;
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        DetalleLogsRecyclerAdapter mAdapter = new DetalleLogsRecyclerAdapter(daoSeries.listarTodos());
        recyclerView.setAdapter(mAdapter);


        return view;
    }
}