package co.edu.uniquindio.SOLID.Service.Fachadas;

import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Service.EmpleadoService;

import java.util.List;

public class EmpresaAdminFacade {

    private final EmpleadoService empleadoService;

    public EmpresaAdminFacade() {
        this.empleadoService = new EmpleadoService();
    }

    public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
        return empleadoService.obtenerTodosLosEmpleados();
    }

    public EmpleadoDTO buscarEmpleadoPorCedula(String id) {
        return empleadoService.buscarEmpleadoPorCedula(id);
    }

    public boolean agregarEmpleado(EmpleadoDTO empleadoDTO) {
        return empleadoService.agregarEmpleado(empleadoDTO);
    }

    public boolean actualizarEmpleado(EmpleadoDTO empleadoDTO) {
        return empleadoService.actualizarEmpleado(empleadoDTO);
    }

    public boolean activarEmpleado(EmpleadoDTO empleadoDTO) {
        return empleadoService.activarEmpleado(empleadoDTO);
    }

    public boolean inactivarEmpleado(EmpleadoDTO empleadoDTO) {
        return empleadoService.inactivarEmpleado(empleadoDTO);
    }

    public boolean eliminarEmpleado(String id) {
        return empleadoService.eliminarEmpleado(id);
    }

}
