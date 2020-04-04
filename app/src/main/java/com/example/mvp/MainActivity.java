package com.example.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mvp.Presentador.LoginPresentador;

public class MainActivity extends AppCompatActivity implements LoginPresentador.View {
    private LoginPresentador loginPresentador;
    EditText contraseña;
    EditText usuario;
    Button loginUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contraseña = findViewById(R.id.contraseña);
        usuario = findViewById(R.id.usuario);
        loginUsuario = findViewById(R.id.loginUsuario);


        loginPresentador = new LoginPresentador(this);
        loginUsuario.setOnClickListener(v -> Login());

        usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresentador.setDataName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        contraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginPresentador.setDataPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void sendDataLogin(String Name) {
    }

    @Override
  public void sendDataLoginPassword(String Password){
    }

    public void Login(){
       loginPresentador.iniciarSesion();
        if (loginPresentador.isConectedSucces() == true){
            Intent intent = new Intent(this, Tabla.class);
            intent.putExtra("token", loginPresentador.getTokenUser());
            startActivity(intent);
        }
    }
}
