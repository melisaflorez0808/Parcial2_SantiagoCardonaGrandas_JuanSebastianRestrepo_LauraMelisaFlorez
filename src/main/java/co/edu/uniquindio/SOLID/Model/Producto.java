package co.edu.uniquindio.SOLID.Model;

public class Producto {
    private String sku;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String sku, String nombre, double precio) {
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0;
    }

    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
        System.out.println("Stock nuevo de " + this.sku + ": " + this.stock);
    }

    public void disminuirStock(int cantidad) {
        this.stock -= cantidad;
    }

    public String getSku() {return sku;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
    public int getStock() {return stock;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(double precio) {this.precio = precio;}

    @Override
    public String toString() {
        return nombre != null ? nombre : (sku != null ? sku : "");
    }
}
