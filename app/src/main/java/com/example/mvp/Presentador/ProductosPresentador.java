package com.example.mvp.Presentador;

import com.example.mvp.Modelos.ProductosModelo;

import java.util.ArrayList;

public class ProductosPresentador {

    private ProductosModelo productoModelo;
    private View view;


    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> precios = new ArrayList<>();
    ArrayList<String> descripciones = new ArrayList<>();
    ArrayList<String> tipoProductos = new ArrayList<>();
    ArrayList<String> proveedores = new ArrayList<>();
    ArrayList<Integer> status = new ArrayList<>();
    ArrayList<String> folios = new ArrayList<>();

    public ProductosPresentador(View view) {
        this.view = view;
        this.productoModelo = new ProductosModelo();
    }

    public void setDataToken(String Token){
        productoModelo.setToken(Token);
        view.sendDataToken(Token);
    }

    public void setDataName(String Name){
        productoModelo.setNombre(Name);
        view.sendDataName(Name);
    }

    public void setDataPrecio(double Precio){
        productoModelo.setPrecio(Precio);
        view.sendDataPrecio(Precio);
    }

    public void setDataDescripcion(String Descripcion){
        productoModelo.setDescripcion(Descripcion);
        view.sendDataDescripcion(Descripcion);
    }

    public void setDataTProducto(String TProducto){
        productoModelo.setTipoProducto(TProducto);
        view.sendDataTProducto(TProducto);
    }

    public void setDataProveedor(String Proveedor){
        productoModelo.setProveedor(Proveedor);
        view.sendDataProveedor(Proveedor);
    }

    public void setDataStatus(int Status){
        productoModelo.setStatus(Status);
        view.sendDataStatus(Status);
    }

    public void setDataFolio(String Folio){
        productoModelo.setFolio(Folio);
        view.sendDataFolio(Folio);
    }

    public interface View {
        void sendDataToken(String Token);
        void sendDataName(String Name);
        void sendDataPrecio(double Precio);
        void sendDataDescripcion(String Descripcion);
        void sendDataTProducto(String TProducto);
        void sendDataProveedor(String Proveedor);
        void sendDataStatus(int Status);
        void sendDataFolio(String Folio);
    }

    public void showDatas(){
        productoModelo.getData();
    }

    public void SendPost(){
        productoModelo.sendDataToServer();
    }

    public ArrayList<Integer> getIds() {
        ids = productoModelo.getIds();
        return ids;
    }

    public void setIdDeleteProducto(int idProducto){
        productoModelo.DeleteU(idProducto);
    }


    public void setIdUpdateProduct(int idUpdateProduct){
        productoModelo.UpdateData(idUpdateProduct);
    }


    public ArrayList<String> getNombres() {
        nombres = productoModelo.getNames();
        return nombres;
    }

    public ArrayList<String> getPrecios() {
        precios = productoModelo.getPrecios();
        return precios;
    }

    public ArrayList<String> getDescripciones() {
        descripciones = productoModelo.getDescripciones();
        return descripciones;
    }

    public ArrayList<String> getTipoProductos() {
        tipoProductos = productoModelo.getTipoProductos();
        return tipoProductos;
    }

    public ArrayList<String> getProveedores() {
        proveedores = productoModelo.getProveedores();
        return proveedores;
    }

    public ArrayList<Integer> getStatuses() {
        status = productoModelo.getStatuses();
        return status;
    }

    public ArrayList<String> getFolios() {
        folios = productoModelo.getFolios();
        return folios;
    }
}
