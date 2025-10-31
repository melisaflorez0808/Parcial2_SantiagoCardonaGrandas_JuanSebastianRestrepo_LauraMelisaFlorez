package co.edu.uniquindio.SOLID.Service;

import co.edu.uniquindio.SOLID.Model.Cliente;
import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.utils.Mappers.ClienteMapper;
import co.edu.uniquindio.SOLID.utils.Mappers.EmpleadoMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private final Minimercado minimercado;

    public EmpleadoService() {
        this.minimercado = Minimercado.getInstancia();
    }

    public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
        List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
        for (Empleado empleado : minimercado.getEmpleados()) {
            empleadosDTO.add(EmpleadoMapper.toDTO(empleado));
        }
        return empleadosDTO;
    }

    public EmpleadoDTO buscarEmpleadoPorCedula(String cedula) {
        Empleado empleado = buscarEmpleadoEntity(cedula);
        return empleado != null ? EmpleadoMapper.toDTO(empleado) : null;
    }

    public boolean agregarEmpleado(EmpleadoDTO empleadoDTO) {
        if (buscarEmpleadoEntity(empleadoDTO.getId()) != null) {
            return false;
        }
        Empleado empleado = EmpleadoMapper.toEntity(empleadoDTO);
        minimercado.addEmpleado(empleado);
        return true;
    }

    public boolean actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = buscarEmpleadoEntity(empleadoDTO.getId());
        if (empleado == null) {
            return false;
        }
        EmpleadoMapper.updateEntityFromDTO(empleado, empleadoDTO);
        return true;
    }

    public boolean eliminarEmpleado(String id) {
        Empleado empleado = buscarEmpleadoEntity(id);
        if (empleado == null) {
            return false;
        }
        minimercado.getEmpleados().remove(empleado);
        return true;
    }

    public boolean existeEmpleado(String id) {
        return buscarEmpleadoEntity(id) != null;
    }

    public Empleado buscarEmpleadoEntity(String cedula) {
        for (Empleado empleado : minimercado.getEmpleados()) {
            if (empleado.getId().equals(cedula)) {
                return empleado;
            }
        }
        return null;
    }

    public boolean inactivarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = buscarEmpleadoEntity(empleadoDTO.getId());
        if (empleado == null) {
            return false;
        }
        empleado.setActivo(false);
        return true;
    }

    public boolean activarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = buscarEmpleadoEntity(empleadoDTO.getId());
        if (empleado == null) {
            return false;
        }
        empleado.setActivo(true);
        return true;
    }


}
