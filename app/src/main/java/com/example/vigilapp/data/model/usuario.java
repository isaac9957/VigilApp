package com.example.vigilapp.data.model;

public class usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String password;
    private String rol;
    private boolean aprobado;
    private int colegioId;

    public usuario() {}

    public usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    public int getColegioId() { return colegioId; }
    public void setColegioId(int colegioId) { this.colegioId = colegioId; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}