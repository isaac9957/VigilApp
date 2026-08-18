package com.example.vigilapp.data.model;

public class LoginResponse {
    private boolean success;
    private String message;
    private usuario usuario;
    private String token;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public usuario getUsuario() { return usuario; }
    public void setUsuario(usuario usuario) { this.usuario = usuario; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}