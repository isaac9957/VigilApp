package com.example.vigilapp.data.model;

public class Alerta {
    private int idAlerta;
    private int usuarioId;
    private int tipoAlertaId;
    private int ubicacionId;
    private String fechaHora;
    private String estado;
    private String descripcion;
    private String imagenUrl;

    private String ubicacionNombre;
    private String tipoAlertaNombre;
    private String usuarioNombre;

    public Alerta() {}

    public int getIdAlerta() { return idAlerta; }
    public void setIdAlerta(int idAlerta) { this.idAlerta = idAlerta; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getTipoAlertaId() { return tipoAlertaId; }
    public void setTipoAlertaId(int tipoAlertaId) { this.tipoAlertaId = tipoAlertaId; }

    public int getUbicacionId() { return ubicacionId; }
    public void setUbicacionId(int ubicacionId) { this.ubicacionId = ubicacionId; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getUbicacionNombre() { return ubicacionNombre; }
    public void setUbicacionNombre(String ubicacionNombre) { this.ubicacionNombre = ubicacionNombre; }

    public String getTipoAlertaNombre() { return tipoAlertaNombre; }
    public void setTipoAlertaNombre(String tipoAlertaNombre) { this.tipoAlertaNombre = tipoAlertaNombre; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }

    public boolean isActiva() {
        return "activa".equals(estado) || "en_proceso".equals(estado);
    }
}