package co.edu.uniquindio.SOLID.Service.Pago;

public class PagoPSE implements MetodoPago {
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago vía PSE por un monto de: " + monto);
        return true;
    }

}