package com.example.vigilapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vigilapp.R;
import com.example.vigilapp.data.model.LoginResponse;
import com.example.vigilapp.data.model.usuario;
import com.example.vigilapp.data.repository.VigilRepository;
import com.example.vigilapp.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView tvError;

    private VigilRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        repository = new VigilRepository(this);

        // Verificar si ya está logueado
        if (repository.getSessionManager().isLoggedIn()) {
            irAHome();
            return;
        }

        // Vincular vistas
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLogin();
            }
        });

        // Observar carga
        repository.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnLogin.setEnabled(false);
                } else {
                    progressBar.setVisibility(View.GONE);
                    btnLogin.setEnabled(true);
                }
            }
        });

        // Observar errores
        repository.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null && !error.isEmpty()) {
                    tvError.setText(error);
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });

        // Observar resultado del login
        repository.getLoginResult().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse response) {
                if (response != null) {
                    if (response.isSuccess()) {
                        Toast.makeText(LoginActivity.this,
                                "Bienvenido " + response.getUsuario().getNombre(),
                                Toast.LENGTH_SHORT).show();
                        irAHome();
                    } else {
                        tvError.setText(response.getMessage());
                        tvError.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void realizarLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            tvError.setText("Ingresa tu correo y contraseña");
            tvError.setVisibility(View.VISIBLE);
            return;
        }

        tvError.setVisibility(View.GONE);
        usuario usuario = new usuario(email, password);
        repository.login(usuario);
    }

    private void irAHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}