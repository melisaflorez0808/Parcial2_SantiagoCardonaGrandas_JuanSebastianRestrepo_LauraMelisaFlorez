package co.edu.uniquindio.SOLID.Model.DTO;

import co.edu.uniquindio.SOLID.Model.ItemEntrada;
import co.edu.uniquindio.SOLID.Model.MovimientoInventario;
import co.edu.uniquindio.SOLID.Model.Proveedor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaInventarioDTO {
    public static int contador = 1;
    private String id;
    private String nitProveedor;
    private LocalDateTime fecha;
    private List<ItemEntradaDTO> items;

    public EntradaInventarioDTO() {}

    public EntradaInventarioDTO(String nitProveedor, List<ItemEntradaDTO> items) {
        this.id = "0" + contador;
        contador += 1;
        this.nitProveedor = nitProveedor;
        this.fecha = LocalDateTime.now();
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveedor() {
        return nitProveedor;
    }

    public void setProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public List<ItemEntradaDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemEntradaDTO> items) {
        this.items = items;
    }
}
