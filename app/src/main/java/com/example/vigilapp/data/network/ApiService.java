package com.example.vigilapp.data.network;

import com.example.vigilapp.data.model.Alerta;
import com.example.vigilapp.data.model.ApiResponse;
import com.example.vigilapp.data.model.LoginResponse;
import com.example.vigilapp.data.model.TipoAlerta;
import com.example.vigilapp.data.model.Ubicacion;
import com.example.vigilapp.data.model.usuario;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body usuario usuario);

    @POST("register")
    Call<ApiResponse<usuario>> register(@Body usuario usuario);

    @GET("usuarios/pendientes")
    Call<ApiResponse<usuario>> getUsuariosPendientes(@Header("Authorization") String token);

    @POST("usuarios/aprobar")
    Call<ApiResponse<usuario>> aprobarUsuario(
            @Header("Authorization") String token,
            @Body usuario usuario
    );

    @POST("usuarios/rechazar")
    Call<ApiResponse<Void>> rechazarUsuario(
            @Header("Authorization") String token,
            @Body usuario usuario
    );

    @POST("alertas")
    Call<ApiResponse<Alerta>> crearAlerta(
            @Header("Authorization") String token,
            @Body Alerta alerta
    );

    @GET("alertas")
    Call<ApiResponse<Alerta>> getAlertas(@Header("Authorization") String token);

    @GET("alertas/activas")
    Call<ApiResponse<Alerta>> getAlertasActivas(@Header("Authorization") String token);

    @GET("alertas/{id}")
    Call<ApiResponse<Alerta>> getAlerta(
            @Header("Authorization") String token,
            @Path("id") int id
    );

    @POST("alertas/{id}/resolver")
    Call<ApiResponse<Void>> resolverAlerta(
            @Header("Authorization") String token,
            @Path("id") int id
    );

    @POST("alertas/{id}/etapa")
    Call<ApiResponse<Void>> avanzarEtapa(
            @Header("Authorization") String token,
            @Path("id") int id
    );

    @GET("ubicaciones")
    Call<ApiResponse<Ubicacion>> getUbicaciones(@Header("Authorization") String token);

    @GET("tipos-alerta")
    Call<ApiResponse<TipoAlerta>> getTiposAlerta(@Header("Authorization") String token);

    @Multipart
    @POST("upload")
    Call<ApiResponse<String>> uploadImage(
            @Header("Authorization") String token,
            @Part MultipartBody.Part imagen
    );
}