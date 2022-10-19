package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.io.File;
import java.io.IOException;


public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        String metodoPagoPreferidoString = sharedPreferences.getString("metodoPagoPreferido", "def");
        if(metodoPagoPreferidoString.equals("Efectivo")) {
            findPreference("monedaPreferida").setEnabled(true);
        }

        ListPreference  metodoPagoPreferidoList = getPreferenceManager().findPreference("metodoPagoPreferido");
        metodoPagoPreferidoList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.toString().equals("Efectivo")){
                    findPreference("monedaPreferida").setEnabled(true);
                }else{
                    findPreference("monedaPreferida").setEnabled(false);
                }
                return true;
            }
        });

        CheckBoxPreference check_box_preference_informacion = getPreferenceManager().findPreference("check_box_preference_informacion");
        check_box_preference_informacion.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(!check_box_preference_informacion.isChecked()){
                    getContext().deleteFile("LogsBusquedaFile.json");
                }
                return true;
            }
        });



    }


}