package co.edu.uniquindio.SOLID.Service;
import co.edu.uniquindio.SOLID.Model.*;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ItemEntradaDTO;

import java.util.List;

public class InventarioService {
    private final Minimercado minimercado;


    public InventarioService() {
        this.minimercado = Minimercado.getInstancia();
    }

    public void registrarEntrada(EntradaInventarioDTO dto) {
        Proveedor proveedor = buscarProveedorPorNit(dto.getNitProveedor());
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no existe");
        }
        if (dto == null) {
            throw new IllegalArgumentException("No ha seleccionado una entrada valida");
        }
        EntradaInventario entrada = new EntradaInventario(dto.getId(), proveedor);

        for (ItemEntradaDTO itemDTO : dto.getItems()) {
            Producto productoEncontrado = null;
            for (Producto producto : minimercado.getProductos()) {
                if (producto.getSku().equals(itemDTO.getSkuProducto())) {
                    productoEncontrado = producto;
                    break;
                }
            }
            if (productoEncontrado == null) {
                throw new IllegalArgumentException("Producto no encontrado: " + itemDTO.getSkuProducto());
            }
            entrada.agregarItem(new ItemEntrada(productoEncontrado, itemDTO.getCantidad()));
            System.out.println("Item DTO: " + itemDTO.getSkuProducto() + " cantidad: " + itemDTO.getCantidad());
        }
        confirmarEntrada(entrada);
    }

    public void confirmarEntrada(EntradaInventario entrada) {
        for (ItemEntrada item : entrada.getItems()) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            if (cantidad <= 0) {
                System.out.println("No se puede ingresar cantidad <= 0 para el producto " + producto.getSku());
                break;
            }
            System.out.println("Aumentando stock de " + producto.getSku() + " en " + cantidad);
            producto.aumentarStock(cantidad);
            MovimientoInventario mov = new MovimientoInventario(
                    "MOV-" + System.currentTimeMillis(),
                    MovimientoInventario.Tipo.ENTRADA,
                    producto,
                    cantidad,
                    entrada.getId()
            );
            minimercado.registrarMovimiento(mov);
        }
    }

    private Proveedor buscarProveedorPorNit(String nit) {
        for (Proveedor p : minimercado.getProveedores()){
            if (p.getNit().equals(nit)) return p;
        }
        return null;
    }

    public Producto buscarProducto(String sku) {
        for (Producto p : minimercado.getProductos()) {
            if (p.getSku().equals(sku)) return p;
        }
        return null;
    }


    public void retirarProducto(String sku, int cantidad) {
        Producto producto = null;
        for (Producto p : minimercado.getProductos()) {
            if (p.getSku().equals(sku)) {
                producto = p;
                break;
            }
        }
        if (producto == null) {
            System.out.println("Producto no encontrado: " + sku);
            return;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad a retirar debe ser mayor a 0");
            return;
        }
        if (cantidad > producto.getStock()) {
            System.out.println("No hay suficiente stock. Stock actual: " + producto.getStock());
            return;
        }
        producto.disminuirStock(cantidad);

        MovimientoInventario mov = new MovimientoInventario(
                "MOV-" + System.currentTimeMillis(),
                MovimientoInventario.Tipo.SALIDA,
                producto,
                cantidad,
                "RET-" + System.currentTimeMillis()
        );
        minimercado.registrarMovimiento(mov);
    }
}