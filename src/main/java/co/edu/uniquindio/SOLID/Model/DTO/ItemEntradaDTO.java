package co.edu.uniquindio.SOLID.Model.DTO;

public class ItemEntradaDTO {

    private String skuProducto;
    private int cantidad;

    public ItemEntradaDTO(String skuProducto, int cantidad) {
        this.skuProducto = skuProducto;
        this.cantidad = cantidad;
    }

    public String getSkuProducto() { return skuProducto; }
    public int getCantidad() { return cantidad; }
}

