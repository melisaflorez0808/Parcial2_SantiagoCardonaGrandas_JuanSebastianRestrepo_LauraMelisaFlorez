package co.edu.uniquindio.SOLID.Service.Descuento;

import co.edu.uniquindio.SOLID.Model.DTO.PedidoDTO;
import co.edu.uniquindio.SOLID.Model.Pedido;

public class DescuentoCantidadFija implements EstrategiaDescuento {
    private final String codigo;
    private final double cantidadFija;

    public DescuentoCantidadFija(String codigo, double cantidadFija) {
        this.codigo = codigo;
        this.cantidadFija = cantidadFija;
    }

    @Override
    public double calcularDescuento(double subtotal, PedidoDTO pedido) {
        return Math.min(subtotal, this.cantidadFija);
    }

    @Override
    public boolean esAplicable(PedidoDTO pedido) {
        return true;
    }

    @Override
    public String getCodigo() { return codigo; }

}