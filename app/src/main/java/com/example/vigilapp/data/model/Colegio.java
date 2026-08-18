package com.example.vigilapp.data.model;

public class Colegio {
    private int idColegio;
    private String nombre;
    private String direccion;
    private String telefono;

    public Colegio() {}

    public int getIdColegio() { return idColegio; }
    public void setIdColegio(int idColegio) { this.idColegio = idColegio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}