package co.edu.uniquindio.SOLID.Model.DTO;

import co.edu.uniquindio.SOLID.Model.ItemEntrada;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaInventarioDTO {
    private String id;
    private Proveedor proveedor;
    private LocalDateTime fecha;
    private List<ItemEntrada> items;
    private boolean confirmada;
    private List<MovimientoInventario> movimientosGenerados;

    public EntradaInventarioDTO(String id, Proveedor proveedor) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.confirmada = false;
        this.movimientosGenerados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<ItemEntrada> getItems() {
        return items;
    }

    public void setItems(List<ItemEntrada> items) {
        this.items = items;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public List<MovimientoInventario> getMovimientosGenerados() {
        return movimientosGenerados;
    }

    public void setMovimientosGenerados(List<MovimientoInventario> movimientosGenerados) {
        this.movimientosGenerados = movimientosGenerados;
    }
}
