package com.mdgz.dam.labdam2022;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.datasource.OnResult;
import com.mdgz.dam.labdam2022.datasource.room.database.AppDataBase;
import com.mdgz.dam.labdam2022.factory.FavoritoRepositoryFactory;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.FavoritoRepository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {

    private FragmentDetalleAlojamientoBinding binding;
    private int mYear, mMonth, mDay;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
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
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater);
        View view = binding.getRoot();

        int position = Integer.parseInt(getArguments().getString("position"));
        Alojamiento alojamiento = getArguments().getParcelable("alojamientoSeleccionado");
        binding.textViewDetalleTipoAlojamiento.setText(getArguments().getString("tipoAlojamiento"));
        binding.textViewDetalleTitulo.setText(alojamiento.getTitulo());
        binding.textViewDetalleCosto.setText("Costo: "+alojamiento.getPrecioBase().toString());
//        reuse with description
        binding.textViewDetalleUbicacion.setText("Descripcion: "+ alojamiento.getDescripcion());

        binding.editTextDateDetalleFechaDesde.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDateDialog(binding.editTextDateDetalleFechaDesde);
                }
            }
        });

        binding.editTextDateDetalleFechaHasta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDateDialog(binding.editTextDateDetalleFechaHasta);
                }
            }
        });

//        binding.imageButtonFavoritoDetalle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view1) { //TODO aca va la logica de buscar si pertenece a la lista de favoritos
//                if (binding.imageButtonFavoritoDetalle.isEnabled()){
//                    binding.imageButtonFavoritoDetalle.setImageResource(R.drawable.favorito);
//                }
//            }
//        });

        FavoritoRepository favoritoRepository = FavoritoRepositoryFactory.create(getContext());
        String alojamientoId = getArguments().getString("alojamientoId");
        // TODO hardcodeado como se obtiene de la app?
        UUID user_id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        OnResult<List<Favorito>> isfavoritoCallback = new OnResult<List<Favorito>>() {
            @Override
            public void onSuccess(List<Favorito> result) {

                for (Favorito r: result){
                    if (alojamientoId.equals(r.getAlojamientoID().toString())){
                        binding.imageButtonFavoritoDetalle.setImageResource(R.drawable.favorito);
                        binding.imageButtonFavoritoDetalle.setSelected(true);
                    }
                }
            }

            @Override
            public void onError(Throwable exception) {
                exception.printStackTrace();
            }
        };

        AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.recuperarFavoritos(isfavoritoCallback));

        binding.imageButtonFavoritoDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) { //TODO aca va la logica de buscar si pertenece a la lista de favoritos
                if (!binding.imageButtonFavoritoDetalle.isSelected()){

                    OnResult<Favorito> guardarfavoritoCallback = new OnResult<Favorito>() {
                        @Override
                        public void onSuccess(Favorito result) {
                            binding.imageButtonFavoritoDetalle.setImageResource(R.drawable.favorito);
                            binding.imageButtonFavoritoDetalle.setSelected(true);
                        }

                        @Override
                        public void onError(Throwable exception) {
                            exception.printStackTrace();
                        }
                    };
                    AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.guardarFavorito(new Favorito(UUID.fromString(alojamientoId), user_id), guardarfavoritoCallback));
                }
                else{
                    OnResult<Favorito> eliminarfavoritoCallback = new OnResult<Favorito>() {
                        @Override
                        public void onSuccess(Favorito result) {
                            binding.imageButtonFavoritoDetalle.setImageResource(R.drawable.favorite_border);
                            binding.imageButtonFavoritoDetalle.setSelected(false);
                        }

                        @Override
                        public void onError(Throwable exception) {
                            exception.printStackTrace();
                        }
                    };
                    AppDataBase.EXECUTOR_DB.execute(() -> favoritoRepository.eliminarFavorito(new Favorito(UUID.fromString(alojamientoId), user_id), eliminarfavoritoCallback));


                }
            }
        });

        binding.buttonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                Navigation.findNavController(view1).navigate(R.id.action_detalleAlojamientoFragment_to_busquedaFragment);
            }
        });

        return view;
    }

    private void showDateDialog(EditText editTextDateDetalleFecha){
        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our edit text.
                        editTextDateDetalleFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }

}