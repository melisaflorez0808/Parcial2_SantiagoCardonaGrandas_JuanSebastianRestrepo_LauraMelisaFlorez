package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.utils.Mappers.ProveedorMapper;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {
    private final Minimercado minimercado;

    public ProveedorService() {
        this.minimercado = Minimercado.getInstancia();
    }

    /*Create*/
    public boolean agregarProveedor(ProveedorDTO proveedorDTO) {
        if (buscarProvedorEntity(proveedorDTO.getNit()) != null) {
            return false;
        }
        Proveedor proveedor = ProveedorMapper.toEntity(proveedorDTO);
        minimercado.addProveedor(proveedor);
        return true;
    }

    /*Read*/
    public List<ProveedorDTO> obtenerTodosLosRepartidores() {
        List<ProveedorDTO> proveedoresDTOS = new ArrayList<>();
        for (Proveedor proveedor : minimercado.getProveedores()) {
            proveedoresDTOS.add(ProveedorMapper.toDTO(proveedor));
        }
        return proveedoresDTOS;
    }

    /*Update*/
    public boolean actualizarProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = buscarProvedorEntity(proveedorDTO.getNit());
        if (proveedor == null) {
            return false;
        }
        ProveedorMapper.updateEntityFromDTO(proveedor, proveedorDTO);
        return true;
    }

    public boolean inactivarProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = buscarProvedorEntity(proveedorDTO.getNit());
        proveedor.desactivar();
        return true;
    }
    public boolean activarProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = buscarProvedorEntity(proveedorDTO.getNit());
        proveedor.activar();
        return true;
    }

    /*Delete*/
    public boolean eliminarProveedor(String nit) {
        Proveedor proveedor = buscarProvedorEntity(nit);
        if (proveedor == null) {
            return false;
        }
        minimercado.getProveedores().remove(proveedor);
        return true;
    }

    public Proveedor buscarProvedorEntity(String nit) {
        for (Proveedor proveedor : minimercado.getProveedores()) {
            if (proveedor.getNit().equals(nit)) {
                return proveedor;
            }
        }
        return null;
    }

    public ProveedorDTO buscarProveedorPorNit(String nit) {
        Proveedor proveedor = buscarProvedorEntity(nit);
        return proveedor != null ? ProveedorMapper.toDTO(proveedor) : null;
    }
}
