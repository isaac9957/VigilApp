package com.example.vigilapp.data.model;

public class Ubicacion {
    private int idUbicacion;
    private String nombre;
    private String pabellon;
    private String zona;

    public Ubicacion() {}

    public Ubicacion(int id, String nombre) {
        this.idUbicacion = id;
        this.nombre = nombre;
    }

    public int getIdUbicacion() { return idUbicacion; }
    public void setIdUbicacion(int idUbicacion) { this.idUbicacion = idUbicacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPabellon() { return pabellon; }
    public void setPabellon(String pabellon) { this.pabellon = pabellon; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
}