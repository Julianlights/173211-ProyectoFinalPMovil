package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.mvp.Presentador.ProductosPresentador;
import java.util.ArrayList;

public class Tabla extends AppCompatActivity implements ProductosPresentador.View{

    private ProductosPresentador productoPresentador;
    EditText usuario;
    TableLayout table;
    TextView textName;
    TextView textPrecio;
    TextView textdescripcion;
    Button mostrarProducto;
    Button agregarProducto;
    String token;
    String nombre;
    String folio;
    String descripcion;
    String tipoProducto;
    String proveedor;
    int status;
    int idProduct = 0;
    double precio;
    boolean crearoactualizar = false;
    ArrayList<String> Descripciones = new ArrayList<>();
    ArrayList<String> TipoProductos = new ArrayList<>();
    ArrayList<String> Proveedores = new ArrayList<>();
    ArrayList<String> Folios = new ArrayList<>();
    ArrayList<String> Names = new ArrayList<>();
    ArrayList<String> Precios = new ArrayList<>();
    ArrayList<Integer> Statuses = new ArrayList<>();
    ArrayList<Integer> Ids = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla);

        table =  findViewById(R.id.tablelayout);
        usuario = findViewById(R.id.usuario);
        mostrarProducto = findViewById(R.id.mostrarProducto);
        agregarProducto = findViewById(R.id.agregarProducto);

        Bundle extra = getIntent().getExtras();
        token = (String) extra.get("token");

        productoPresentador = new ProductosPresentador(this);
        productoPresentador.setDataToken(token);
        productoPresentador.showDatas();

        mostrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show();
            }
        });

        agregarProducto.setOnClickListener(v -> GoToMasOpciones());
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

    public void Show()
    {
        Ids = productoPresentador.getIds();
        Names = productoPresentador.getNombres();
        Descripciones = productoPresentador.getDescripciones();
        Precios = productoPresentador.getPrecios();
        Proveedores = productoPresentador.getProveedores();
        TipoProductos = productoPresentador.getTipoProductos();
        Statuses = productoPresentador.getStatuses();
        Folios = productoPresentador.getFolios();
        recorrerDatos();
    }

    public void recorrerDatos()
    {
        int NUM_COLS= 1;
        int NUM_ROWS= Names.size();

        for(int i = 0; i<NUM_ROWS; i++){
            TableRow tableRow = new TableRow(getBaseContext());
            table.addView(tableRow);

            for(int j= 0; j<NUM_COLS; j++){
                final Button btnDel = new Button(getBaseContext());
                btnDel.setId(i);
                btnDel.setText("Borrar");

                final Button btnEdit = new Button(getBaseContext());
                btnEdit.setId(i);
                btnEdit.setText("Editar");

                textName = new TextView(getBaseContext());
                textName.setGravity(Gravity.CENTER_VERTICAL);
                textName.setPadding(60, 40, 60, 25);
                textName.setBackgroundResource(R.color.colorAccent);
                textName.setTextColor(Color.WHITE);

                textPrecio  = new TextView(getBaseContext());
                textPrecio.setPadding(60, 40, 60, 25);
                textPrecio.setBackgroundResource(R.color.colorAccent);
                textPrecio.setTextColor(Color.WHITE);

                textName.setText(Names.get(i));
                textPrecio.setText(Precios.get(i));

                tableRow.addView(textName);
                tableRow.addView(textPrecio);

                tableRow.addView(btnEdit);
                tableRow.addView(btnDel);


                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idProduct = Ids.get(btnEdit.getId());
                        crearoactualizar = true;
                        GoToMasOpciones();
                    }
                });

                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idProduct = Ids.get(btnDel.getId());
                        DeleteProduct(idProduct);
                    }
                });

            }
        }
    }

    public void GoToMasOpciones(){
        Intent intent = new Intent(this, Producto.class);
        intent.putExtra("token", token);
        intent.putExtra("idProduct", idProduct);
        intent.putExtra("crearoactualizar", crearoactualizar);
        startActivity(intent);
    }

    public void DeleteProduct(int idProduct){
        productoPresentador.setIdDeleteProducto(idProduct);
    }
}
