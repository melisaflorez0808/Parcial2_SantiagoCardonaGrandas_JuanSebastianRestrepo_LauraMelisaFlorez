package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.ProveedorService;

import java.util.List;

public class InventarioFacade {
    private final ProveedorService proveedorService;

    public InventarioFacade() {
        this.proveedorService = new ProveedorService();
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

    public boolean eliminarProveedor(String nit) {
        return proveedorService.eliminarProveedor(nit);
    }

    public Proveedor buscarProveedorEntity(String id){
        return proveedorService.buscarProvedorEntity(id);
    }
}
