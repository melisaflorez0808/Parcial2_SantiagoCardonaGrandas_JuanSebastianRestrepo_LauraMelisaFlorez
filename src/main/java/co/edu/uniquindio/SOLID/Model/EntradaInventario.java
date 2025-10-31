package co.edu.uniquindio.SOLID.Model;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaInventario {
    private String id;
    private Proveedor proveedor;
    private LocalDateTime fecha;
    private List<ItemEntrada> items;
    private boolean confirmada;
    private List<MovimientoInventario> movimientosGenerados;

    public EntradaInventario(String id, Proveedor proveedor) {
        this.id = id;
        this.proveedor = proveedor;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.confirmada = false;
        this.movimientosGenerados = new ArrayList<>();
    }

    public void agregarItem(ItemEntrada itemEntrada) {
        items.add(itemEntrada);
    }


    public String getId() { return id; }
    public Proveedor getProveedor() { return proveedor; }
    public LocalDateTime getFecha() { return fecha; }
    public List<ItemEntrada> getItems() { return items; }
    public boolean isConfirmada() { return confirmada; }
    public List<MovimientoInventario> getMovimientosGenerados() { return movimientosGenerados; }
}


