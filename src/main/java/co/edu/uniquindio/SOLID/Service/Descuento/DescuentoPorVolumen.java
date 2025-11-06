package co.edu.uniquindio.SOLID.Service.Descuento;

import co.edu.uniquindio.SOLID.Model.DTO.ItemPedidoDTO;
import co.edu.uniquindio.SOLID.Model.DTO.PedidoDTO;


public class DescuentoPorVolumen implements EstrategiaDescuento {
    private final String codigo;
    private final int cantidadMinima;
    private final double porcentaje;

    public DescuentoPorVolumen(String codigo, int cantidadMinima, double porcentaje) {
        this.codigo = codigo;
        this.cantidadMinima = cantidadMinima;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularDescuento(double subtotal, PedidoDTO pedido) {
        return subtotal * (this.porcentaje);
    }

    @Override
    public boolean esAplicable(PedidoDTO pedido) {
        int cantidad=0;
        for (ItemPedidoDTO itemPedido:pedido.itemsPedido){
            cantidad+=itemPedido.cantidad;
        }

        return cantidad > this.cantidadMinima;
    }

    @Override
    public String getCodigo() { return codigo; }

}