package co.edu.uniquindio.SOLID.Service.Descuento;

import co.edu.uniquindio.SOLID.Model.DTO.PedidoDTO;
import co.edu.uniquindio.SOLID.Model.Pedido;

public interface EstrategiaDescuento {
    double calcularDescuento(double subtotal, PedidoDTO pedido);
    String getCodigo();
    boolean esAplicable(PedidoDTO pedido);
}