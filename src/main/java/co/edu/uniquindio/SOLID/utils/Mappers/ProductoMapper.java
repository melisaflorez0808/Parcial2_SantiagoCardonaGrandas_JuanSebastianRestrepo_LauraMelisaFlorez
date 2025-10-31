package co.edu.uniquindio.SOLID.utils.Mappers;

import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.DTO.ProductoDTO;

public class ProductoMapper {
    
    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;
        ProductoDTO dto = new ProductoDTO(
            producto.getSku(),
            producto.getNombre(),
            producto.getPrecio()
        );
        dto.setStock(producto.getStock());
        return dto;
    }
    
    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;
        return new Producto(
            dto.getSku(),
            dto.getNombre(),
            dto.getPrecio()
        );
    }
    
    public static void updateEntityFromDTO(Producto entity, ProductoDTO dto) {
        if (entity == null || dto == null) return;
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
    }
}

