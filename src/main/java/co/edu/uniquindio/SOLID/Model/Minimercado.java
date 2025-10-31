package co.edu.uniquindio.SOLID.Model;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;

import java.util.ArrayList;
import java.util.List;

public class Minimercado {
    List<Producto> productos;
    List<Pedido> pedidos;
    List<Cliente> clientes;
    List<Empleado> empleados;
    List<Proveedor> proveedores;
    List<MovimientoInventario> movimientos;

    private static Minimercado instancia;

    private Minimercado() {
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        clientes = new ArrayList<>();
        empleados = new ArrayList<>();
        proveedores = new ArrayList<>();
        movimientos = new ArrayList<>();
    }

    public static Minimercado getInstancia() {
        if (instancia == null) {
            instancia = new Minimercado();
        }
        return instancia;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void addEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void addProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() { 
        return empleados; 
    }

    public List<Proveedor> getProveedores() { 
        return proveedores; 
    }

    public List<MovimientoInventario> getMovimientos() { 
        return movimientos; 
    }

    public void registrarMovimiento(MovimientoInventario movimiento) { 
        movimientos.add(movimiento); 
    }

}
