package co.edu.uniquindio.SOLID.Model.DTO;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductoDTO {
    private StringProperty sku;
    private StringProperty nombre;
    private DoubleProperty precio;
    private int stock;

    public ProductoDTO() {
        this.sku = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.precio = new SimpleDoubleProperty();
    }

    public ProductoDTO(String sku, String nombre, double precio) {
        this();
        setSku(sku);
        setNombre(nombre);
        setPrecio(precio);
    }

    public String getSku() { return sku.get(); }
    public String getNombre() { return nombre.get(); }
    public double getPrecio() { return precio.get(); }

    public void setSku(String sku) { this.sku.set(sku); }
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setPrecio(double precio) { this.precio.set(precio); }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getNombre() + " - $" + String.format("%.0f", getPrecio());
    }
}
