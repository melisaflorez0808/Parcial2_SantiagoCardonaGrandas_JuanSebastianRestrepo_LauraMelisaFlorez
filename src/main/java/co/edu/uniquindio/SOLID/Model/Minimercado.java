package co.edu.uniquindio.SOLID.Model;

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

    public void addProveedor(Proveedor proveedor) {proveedores.add(proveedor);
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
    

    public void agregarProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            proveedores.add(proveedor);
        }
    }




    // --- Proveedores ---
    public Proveedor crearProveedor(String nit, String nombre, String contacto, String email, String telefono) {
        if (buscarProveedor(nit) != null) {
            throw new IllegalArgumentException("Ya existe un proveedor con ese NIT");
        }
        Proveedor proveedor = new Proveedor(nit, nombre, contacto != null ? contacto : "", email != null ? email : "", telefono != null ? telefono : "");
        proveedores.add(proveedor);
        return proveedor;
    }
    public Proveedor buscarProveedor(String nit) {
        for (Proveedor p : proveedores) { 
            if (p.getNit().equals(nit)) return p; 
        }
        return null;
    }

    public Proveedor actualizarProveedor(String nit, String nombre, String contacto, String email, String telefono, Boolean activo) {
        Proveedor p = buscarProveedor(nit);
        if (p == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        if (nombre != null) p.setNombre(nombre);
        if (contacto != null) p.setContacto(contacto);
        if (email != null) p.setEmail(email);
        if (telefono != null) p.setTelefono(telefono);
        if (activo != null) { if (activo) p.activar(); else p.inactivar(); }
        return p;
    }

    public void eliminarProveedor(String nit) {
        Proveedor p = buscarProveedor(nit);
        if (p == null) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + nit);
        }
        proveedores.remove(p);
    }

    // Nota: la confirmación y registro de movimientos se hace en EntradaInventario.confirmar()
    public EntradaInventario registrarEntradaInventario(Proveedor proveedor, Producto producto, int cantidad) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Se requiere un proveedor");
        }
        if (producto == null) {
            throw new IllegalArgumentException("Se requiere un producto");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        EntradaInventario entrada = new EntradaInventario("ENT-" + System.currentTimeMillis(), proveedor);
        entrada.agregarItem(producto, cantidad);
        entrada.confirmar();
        return entrada;
    }
}
