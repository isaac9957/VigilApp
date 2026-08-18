package com.example.vigilapp.data.model;

public class TipoAlerta {
    private int idTipoAlerta;
    private String nombre;
    private String descripcion;
    private String color;

    public TipoAlerta() {}

    public TipoAlerta(int id, String nombre) {
        this.idTipoAlerta = id;
        this.nombre = nombre;
    }

    public int getIdTipoAlerta() { return idTipoAlerta; }
    public void setIdTipoAlerta(int idTipoAlerta) { this.idTipoAlerta = idTipoAlerta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}