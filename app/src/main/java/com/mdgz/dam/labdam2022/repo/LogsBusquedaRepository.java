package com.mdgz.dam.labdam2022.repo;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.LogsBusqueda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogsBusquedaRepository {

    String FILENAME = "LogsBusquedaFile.json";
    Context ctx;
    private static List<LogsBusqueda> REPOSITORIO_PEDIDOS = new ArrayList<>();

    public LogsBusquedaRepository(Context ctx) {
        this.ctx = ctx;
        cargarLista(ctx);
    }

    public void agregar(LogsBusqueda pedido) {
        REPOSITORIO_PEDIDOS.add(pedido);
        guardarLista();
    }

    public void actualizar(LogsBusqueda pedido) {
        int indice = REPOSITORIO_PEDIDOS.indexOf(pedido);
        REPOSITORIO_PEDIDOS.set(indice, pedido);
        guardarLista();
    }

    public void eliminar(LogsBusqueda pedido) {
        REPOSITORIO_PEDIDOS.remove(pedido);
        guardarLista();
    }

    public List<LogsBusqueda> listarTodos() {
        return REPOSITORIO_PEDIDOS;
    }


    private void cargarLista(Context ctx) {
        try {
            File logsBusquedaFile = new File(ctx.getFilesDir() + "/LogsBusquedaFile.json");

            if (logsBusquedaFile.exists()) {
                JSONArray datos =
                        (JSONArray) new JSONTokener(this.leerDeArchivo()).nextValue();
                REPOSITORIO_PEDIDOS.clear();
                for (int i = 0; i < datos.length(); i++) {
                    JSONObject fila = datos.getJSONObject(i);
                    LogsBusqueda unPedido = new LogsBusqueda();
                    unPedido.loadFromJson(fila);
                    REPOSITORIO_PEDIDOS.add(unPedido);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void guardarLista() {
        JSONArray arregloPedidos = new JSONArray();
        for (LogsBusqueda p : this.REPOSITORIO_PEDIDOS) {
            arregloPedidos.put(p.toJson());
        }
        escribirEnArchivo(arregloPedidos);
    }

    private void escribirEnArchivo(JSONArray arregloPedidos) {
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(arregloPedidos.toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerDeArchivo() {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = ctx.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line;
            while ((line = buffRdr.readLine()) != null) {
                sb.append(line);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
