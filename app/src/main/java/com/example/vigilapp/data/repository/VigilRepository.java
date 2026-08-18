package com.example.vigilapp.data.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.vigilapp.data.local.SessionManager;
import com.example.vigilapp.data.model.Alerta;
import com.example.vigilapp.data.model.ApiResponse;
import com.example.vigilapp.data.model.LoginResponse;
import com.example.vigilapp.data.model.TipoAlerta;
import com.example.vigilapp.data.model.Ubicacion;
import com.example.vigilapp.data.model.usuario;
import com.example.vigilapp.data.network.ApiClient;
import com.example.vigilapp.data.network.ApiService;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VigilRepository {
    private ApiService apiService;
    private SessionManager sessionManager;

    // LiveData para observar cambios
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<usuario>> registerResult = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Alerta>> alertaResult = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Alerta>> alertasList = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Ubicacion>> ubicacionesList = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<TipoAlerta>> tiposAlertaList = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<usuario>> usuariosPendientes = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<Void>> operationResult = new MutableLiveData<>();
    private MutableLiveData<ApiResponse<String>> uploadResult = new MutableLiveData<>();

    public VigilRepository(Context context) {
        apiService = ApiClient.getClient().create(ApiService.class);
        sessionManager = new SessionManager(context);
    }

    // ============ GETTERS ============
    public MutableLiveData<Boolean> getIsLoading() { return isLoading; }
    public MutableLiveData<String> getErrorMessage() { return errorMessage; }
    public MutableLiveData<LoginResponse> getLoginResult() { return loginResult; }
    public SessionManager getSessionManager() { return sessionManager; }

    // ============ LOGIN ============
    public void login(usuario usuario) {
        isLoading.setValue(true);
        apiService.login(usuario).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        sessionManager.saveToken(response.body().getToken());
                        sessionManager.saveUser(response.body().getUsuario());
                    }
                    loginResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ REGISTER ============
    public void register(usuario usuario) {
        isLoading.setValue(true);
        apiService.register(usuario).enqueue(new Callback<ApiResponse<usuario>>() {
            @Override
            public void onResponse(Call<ApiResponse<usuario>> call, Response<ApiResponse<usuario>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    registerResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al registrar");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<usuario>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ CREAR ALERTA ============
    public void crearAlerta(Alerta alerta) {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.crearAlerta(auth, alerta).enqueue(new Callback<ApiResponse<Alerta>>() {
            @Override
            public void onResponse(Call<ApiResponse<Alerta>> call, Response<ApiResponse<Alerta>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    alertaResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al crear alerta");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Alerta>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ GET ALERTAS ============
    public void getAlertas() {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.getAlertas(auth).enqueue(new Callback<ApiResponse<Alerta>>() {
            @Override
            public void onResponse(Call<ApiResponse<Alerta>> call, Response<ApiResponse<Alerta>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    alertasList.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al obtener alertas");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Alerta>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ GET UBICACIONES ============
    public void getUbicaciones() {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.getUbicaciones(auth).enqueue(new Callback<ApiResponse<Ubicacion>>() {
            @Override
            public void onResponse(Call<ApiResponse<Ubicacion>> call, Response<ApiResponse<Ubicacion>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ubicacionesList.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al obtener ubicaciones");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Ubicacion>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ GET TIPOS DE ALERTA ============
    public void getTiposAlerta() {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.getTiposAlerta(auth).enqueue(new Callback<ApiResponse<TipoAlerta>>() {
            @Override
            public void onResponse(Call<ApiResponse<TipoAlerta>> call, Response<ApiResponse<TipoAlerta>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    tiposAlertaList.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al obtener tipos de alerta");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TipoAlerta>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ RESOLVER ALERTA ============
    public void resolverAlerta(int alertaId) {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.resolverAlerta(auth, alertaId).enqueue(new Callback<ApiResponse<Void>>() {
            @Override
            public void onResponse(Call<ApiResponse<Void>> call, Response<ApiResponse<Void>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    operationResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al resolver alerta");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Void>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ UPLOAD IMAGEN ============
    public void uploadImage(MultipartBody.Part imagen) {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.uploadImage(auth, imagen).enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    uploadResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al subir imagen");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ ADMIN - USUARIOS PENDIENTES ============
    public void getUsuariosPendientes() {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.getUsuariosPendientes(auth).enqueue(new Callback<ApiResponse<usuario>>() {
            @Override
            public void onResponse(Call<ApiResponse<usuario>> call, Response<ApiResponse<usuario>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    usuariosPendientes.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al obtener usuarios pendientes");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<usuario>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ APROBAR USUARIO ============
    public void aprobarUsuario(usuario usuario) {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.aprobarUsuario(auth, usuario).enqueue(new Callback<ApiResponse<usuario>>() {
            @Override
            public void onResponse(Call<ApiResponse<usuario>> call, Response<ApiResponse<usuario>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    usuariosPendientes.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al aprobar usuario");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<usuario>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }

    // ============ RECHAZAR USUARIO ============
    public void rechazarUsuario(usuario usuario) {
        isLoading.setValue(true);
        String auth = sessionManager.getAuthHeader();
        if (auth == null) {
            errorMessage.setValue("No autenticado");
            return;
        }

        apiService.rechazarUsuario(auth, usuario).enqueue(new Callback<ApiResponse<Void>>() {
            @Override
            public void onResponse(Call<ApiResponse<Void>> call, Response<ApiResponse<Void>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    operationResult.setValue(response.body());
                } else {
                    errorMessage.setValue("Error al rechazar usuario");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Void>> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error de red: " + t.getMessage());
            }
        });
    }
}