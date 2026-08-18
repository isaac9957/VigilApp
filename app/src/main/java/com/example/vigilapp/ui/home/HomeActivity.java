package com.example.vigilapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vigilapp.R;
import com.example.vigilapp.data.local.SessionManager;
import com.example.vigilapp.data.model.usuario;
import com.example.vigilapp.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private usuario usuarioActual;

    private TextView tvBienvenida, tvRol;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        usuarioActual = sessionManager.getUser();

        // Verificar sesión
        if (!sessionManager.isLoggedIn()) {
            irALogin();
            return;
        }

        // Vincular vistas
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvRol = findViewById(R.id.tvRol);
        btnLogout = findViewById(R.id.btnLogout);

        // Mostrar datos del usuario
        if (usuarioActual != null) {
            tvBienvenida.setText("Hola, " + usuarioActual.getNombre());
            tvRol.setText(usuarioActual.getRol());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion() {
        sessionManager.clear();
        irALogin();
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }

    private void irALogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}