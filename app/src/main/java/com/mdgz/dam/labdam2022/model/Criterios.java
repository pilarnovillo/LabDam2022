package com.mdgz.dam.labdam2022.model;

public class Criterios {

    Boolean hotel;
    Boolean departamento;
    String cantPersonas;
    Boolean wifi;
    String precioMin;
    String precioMax;
    String ciudad;

    public Criterios() {
    }

    public Criterios(Boolean hotel, Boolean departamento, String cantPersonas, Boolean wifi, String precioMin, String precioMax, String ciudad) {
        this.hotel = hotel;
        this.departamento = departamento;
        this.cantPersonas = cantPersonas;
        this.wifi = wifi;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.ciudad = ciudad;
    }

    public Boolean getHotel() {
        return hotel;
    }

    public void setHotel(Boolean hotel) {
        this.hotel = hotel;
    }

    public Boolean getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Boolean departamento) {
        this.departamento = departamento;
    }

    public String getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(String cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public String getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(String precioMin) {
        this.precioMin = precioMin;
    }

    public String getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(String precioMax) {
        this.precioMax = precioMax;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Criterios:{" +
                "'hotel':" + hotel +
                ", 'departamento':" + departamento +
                ", 'cantPersonas':'" + cantPersonas + '\'' +
                ", 'wifi':" + wifi +
                ", 'precioMin':'" + precioMin + '\'' +
                ", 'precioMax':'" + precioMax + '\'' +
                ", 'ciudad':'" + ciudad + '\'' +
                '}';
    }
}
