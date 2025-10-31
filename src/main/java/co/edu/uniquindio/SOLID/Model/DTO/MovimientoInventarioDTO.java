package co.edu.uniquindio.SOLID.Model.DTO;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;

import java.time.LocalDateTime;

public class MovimientoInventarioDTO {
    private String id;
    private MovimientoInventario.Tipo tipo;
    private LocalDateTime fecha;
    private String sku;
    private int cantidad;
    private String referencia;

    public MovimientoInventarioDTO() {}

    public MovimientoInventarioDTO(String id, MovimientoInventario.Tipo tipo,
                                   LocalDateTime fecha, String sku, int cantidad,
                                   String referencia) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.sku = sku;
        this.cantidad = cantidad;
        this.referencia = referencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MovimientoInventario.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(MovimientoInventario.Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
