package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.Cliente;
import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;

public class EmpleadoMapper {

    public static EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null) return null;
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getRol());
    }

    public static Empleado toEntity(EmpleadoDTO dto) {
        if (dto == null) return null;
        return new Empleado(
                dto.getId(),
                dto.getNombre(),
                dto.getRol());
    }

    public static void updateEntityFromDTO(Empleado entity, EmpleadoDTO dto) {
        if (entity == null || dto == null) return;
        entity.setNombre(dto.getNombre());
        entity.setRol(dto.getRol());
    }

}
