package co.edu.uniquindio.SOLID.Service.Fachadas;
import co.edu.uniquindio.SOLID.Model.*;
import co.edu.uniquindio.SOLID.Model.DTO.EntradaInventarioDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Service.InventarioService;
import co.edu.uniquindio.SOLID.Service.ProveedorService;
import java.util.List;

public class InventarioFacade {
    private final ProveedorService proveedorService;
    private final InventarioService inventarioService;

    public InventarioFacade() {
        this.proveedorService = new ProveedorService();
        this.inventarioService = new InventarioService();
    }

    /*PROVEEDORES*/
    public boolean agregarProveedor(ProveedorDTO proveedorDTO) {
        return proveedorService.agregarProveedor(proveedorDTO);
    }

    public List<ProveedorDTO> getProveedores() {
        return proveedorService.obtenerTodosLosRepartidores();
    }

    public boolean actualizarProveedor(ProveedorDTO proveedorDTO) {
        return proveedorService.actualizarProveedor(proveedorDTO);
    }

    public boolean inactivarProveedor(ProveedorDTO proveedorDTO) {
        return proveedorService.inactivarProveedor(proveedorDTO);
    }
    public boolean activarProveedor(ProveedorDTO proveedorDTO){
        return proveedorService.activarProveedor(proveedorDTO);
    }

    public boolean eliminarProveedor(String nit) {
        return proveedorService.eliminarProveedor(nit);
    }

    public ProveedorDTO buscarProveedorDTO(String nit){
        return proveedorService.buscarProveedorPorNit(nit);
    }

    /*INVENTARIO*/

    public void registrarEntrada(EntradaInventarioDTO dto) {
        inventarioService.registrarEntrada(dto);
    }

    private void confirmarEntrada(EntradaInventario entrada) {
        inventarioService.confirmarEntrada(entrada);
    }

    public void retirarProducto(String sku, int cantidad) {
        inventarioService.retirarProducto(sku, cantidad);
    }
}
