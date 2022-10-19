package com.mdgz.dam.labdam2022.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class LogsBusqueda {
    String timestamp;
    String criteriosBusqueda;
    int cantResultados;
    String tiempoBusqueda;

    public LogsBusqueda() {
    }

    public LogsBusqueda(String timestamp, String criteriosBusqueda, int cantResultados, String tiempoBusqueda) {
        this.timestamp = timestamp;
        this.criteriosBusqueda = criteriosBusqueda;
        this.cantResultados = cantResultados;
        this.tiempoBusqueda = tiempoBusqueda;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCriteriosBusqueda() {
        return criteriosBusqueda;
    }

    public void setCriteriosBusqueda(String criteriosBusqueda) {
        this.criteriosBusqueda = criteriosBusqueda;
    }

    public int getCantResultados() {
        return cantResultados;
    }

    public void setCantResultados(int cantResultados) {
        this.cantResultados = cantResultados;
    }

    public String getTiempoBusqueda() {
        return tiempoBusqueda;
    }

    public void setTiempoBusqueda(String tiempoBusqueda) {
        this.tiempoBusqueda = tiempoBusqueda;
    }

    public JSONObject toJson(){
        JSONObject unLogBusqueda = new JSONObject();
        try {
            unLogBusqueda.put("timestamp",this.getTimestamp());
            unLogBusqueda.put("criterios",getCriteriosBusqueda());
            unLogBusqueda.put("cantResultados",this.getCantResultados());
            unLogBusqueda.put("tiempoBusqueda",this.getTiempoBusqueda());
        } catch (JSONException e) { e.printStackTrace(); }
        return unLogBusqueda;
    }

    public void loadFromJson(JSONObject fila ){
        try {
            this.setTimestamp(fila.getString("timestamp"));
            this.setCriteriosBusqueda(fila.getString("criterios"));
            this.setCantResultados(fila.getInt("cantResultados"));
            this.setTiempoBusqueda(fila.getString("tiempoBusqueda"));

        } catch (JSONException e) {e.printStackTrace();}
    }


}
