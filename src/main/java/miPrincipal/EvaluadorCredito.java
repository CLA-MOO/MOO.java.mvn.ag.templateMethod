package miPrincipal;

import java.util.Locale;

public abstract class EvaluadorCredito {
    public final String evaluarSolicitud(String cliente, double ingresosMensuales, double montoSolicitado) {
        
    }

    private void validarDatos(String cliente, double ingresosMensuales, double montoSolicitado) {
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (ingresosMensuales <= 0 || montoSolicitado <= 0) {
            throw new IllegalArgumentException("Los montos deben ser mayores que cero");
        }
    }
}
