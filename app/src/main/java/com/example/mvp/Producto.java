package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvp.Presentador.ProductosPresentador;

public class Producto extends AppCompatActivity implements ProductosPresentador.View{
    private ProductosPresentador productoPresentador;

    Button login;
    EditText nombre;
    EditText precio;
    EditText descripcion;
    EditText tipoProducto;
    EditText proveedor;
    EditText estado;
    EditText folio;
    String token;
    int idProduct = 0;
    boolean crearoactualizar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        nombre = findViewById(R.id.nombre);
        precio = findViewById(R.id.precio);
        descripcion = findViewById(R.id.descripcion);
        tipoProducto = findViewById(R.id.tipoProducto);
        proveedor = findViewById(R.id.proveedor);
        estado = findViewById(R.id.estado);
        folio = findViewById(R.id.folio);
        login = findViewById(R.id.login);

        Bundle extra = getIntent().getExtras();
        token = (String) extra.get("token");
        idProduct = (int) extra.get("idProduct");
        crearoactualizar = (boolean) extra.get("crearoactualizar");

        productoPresentador = new ProductosPresentador(this);
        productoPresentador.setDataToken(token);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (crearoactualizar == false){
                    productoPresentador.SendPost();
                    GoToDashboard();
                }else{
                    productoPresentador.setIdUpdateProduct(idProduct);
                    GoToDashboard();
                }
            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productoPresentador.setDataName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        precio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               productoPresentador.setDataPrecio(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        descripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productoPresentador.setDataDescripcion(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        tipoProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productoPresentador.setDataTProducto(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        proveedor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productoPresentador.setDataProveedor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        estado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               productoPresentador.setDataStatus(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        folio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productoPresentador.setDataFolio(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void sendDataToken(String Token) {}
    @Override
    public void sendDataName(String Name) {}
    @Override
    public void sendDataPrecio(double Precio) {}
    @Override
    public void sendDataDescripcion(String Descripcion) {}
    @Override
    public void sendDataTProducto(String TProducto) {}
    @Override
    public void sendDataProveedor(String Proveedor) {}
    @Override
    public void sendDataStatus(int Status) {}
    @Override
    public void sendDataFolio(String Folio) {}

    public void GoToDashboard(){
        Intent intent = new Intent(this, Tabla.class);
        startActivity(intent);
    }
}
