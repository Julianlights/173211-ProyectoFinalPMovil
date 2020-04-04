package com.example.mvp.Modelos;

import com.example.mvp.Urls.UrlsApis;
import com.example.mvp.Ngrok.Ngrok;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class ProductosModelo {

    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams params =  new  RequestParams ();

    String token;
    String nombre;
    double precio;
    String descripcion;
    String tipoProducto;
    String proveedor;
    int status;
    String folio;

    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> precios = new ArrayList<>();
    ArrayList<String> descripciones = new ArrayList<>();
    ArrayList<String> tipoProductos = new ArrayList<>();
    ArrayList<String> proveedores = new ArrayList<>();
    ArrayList<Integer> statuses = new ArrayList<>();
    ArrayList<String> folios = new ArrayList<>();


    public void setToken(String token) {
        this.token = token;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setFolio(String folio) {
        this.folio = folio;
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }
    public ArrayList<String> getNames() {
        return names;
    }
    public ArrayList<String> getPrecios() {
        return precios;
    }
    public ArrayList<String> getDescripciones() {
        return descripciones;
    }
    public ArrayList<String> getTipoProductos() {
        return tipoProductos;
    }
    public ArrayList<String> getProveedores() {
        return proveedores;
    }
    public ArrayList<Integer> getStatuses() {
        return statuses;
    }
    public ArrayList<String> getFolios() {
        return folios;
    }

    public void getHeaders(){
        String head = "Token " + token;
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        client.addHeader("Authorization", head);
    }

    public void getData(){
        getHeaders();

        client.get(Ngrok.URL + UrlsApis.GetProducts,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONArray jsonArray = new JSONArray(new String(responseBody));
                    for (int i=0;i<jsonArray.length(); i++) {

                        ids.add(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
                        names.add(jsonArray.getJSONObject(i).getString("nombre"));
                        precios.add(jsonArray.getJSONObject(i).getString("precio"));

                        descripciones.add(jsonArray.getJSONObject(i).getString("descripcion"));
                        tipoProductos.add(jsonArray.getJSONObject(i).getString("tipoProducto"));
                        proveedores.add(jsonArray.getJSONObject(i).getString("proveedor"));
                        statuses.add(Integer.parseInt(jsonArray.getJSONObject(i).getString("status")));
                        folios.add(jsonArray.getJSONObject(i).getString("folio"));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

        public void sendDataToServer(){
            getHeaders();

            params.put("nombre", nombre);
            params.put("precio", precio);
            params.put("descripcion", descripcion);
            params.put("tipoProducto", tipoProducto);
            params.put("proveedor", proveedor);
            params.put("status", status);
            params.put("folio", folio);

            client.post(Ngrok.URL + UrlsApis.PostProducts, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                }
            });
        }


        public void DeleteU(int idSelected){
            getHeaders();

            client.delete(Ngrok.URL + UrlsApis.DeleteProducts + idSelected + "/", new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                }
            });
        }


        public void UpdateData(int idSelected){
            getHeaders();

            params.put("nombre", nombre);
            params.put("precio", precio);
            params.put("descripcion", descripcion);
            params.put("tipoProducto", tipoProducto);
            params.put("proveedor", proveedor);
            params.put("status", status);
            params.put("folio", folio);

            client.put(Ngrok.URL + UrlsApis.UpdateProducts + idSelected + "/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                }
            });
        }
}
