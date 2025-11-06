package co.edu.uniquindio.SOLID.Service.Descuento;

import java.util.ArrayList;
import java.util.List;

public class GestorDescuentos {
    private final List<EstrategiaDescuento> estrategias = new ArrayList<>();

    public GestorDescuentos() {
        estrategias.add(new DescuentoPorcentaje("porcentaje",0.1));
        estrategias.add(new DescuentoCantidadFija("fijo",6000));
        estrategias.add(new DescuentoPorVolumen("volumen",5,0.15));
    }

    public EstrategiaDescuento obtenerEstrategia(String codigo) {
        return estrategias.stream()
                .filter(e -> e.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}
