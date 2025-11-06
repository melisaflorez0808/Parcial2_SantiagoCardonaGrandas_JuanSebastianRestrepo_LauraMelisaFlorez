package co.edu.uniquindio.SOLID.Service.Descuento;

import co.edu.uniquindio.SOLID.Model.DTO.PedidoDTO;
import co.edu.uniquindio.SOLID.Model.Pedido;

public class DescuentoPorcentaje implements EstrategiaDescuento {
    private final String codigo;
    private final double porcentaje;

    public DescuentoPorcentaje(String codigo, double porcentaje) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double subtotal, PedidoDTO pedido) {
        return subtotal * (this.porcentaje);
    }

    @Override
    public boolean esAplicable(PedidoDTO pedido) {
        return true;
    }

    @Override
    public String getCodigo() { return codigo; }


}