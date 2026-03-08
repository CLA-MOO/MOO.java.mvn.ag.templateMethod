package miPrincipal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class AppTest {
    private static final String SALTO = System.lineSeparator();

    @Test
    public void debeEvaluarUnPrestamoPersonalAprobado() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoPersonal();

        String resultado = evaluador.evaluarSolicitud("Ana Torres", 3200.0, 9000.0);

        assertEquals(
                "Producto: Préstamo personal" + SALTO
                        + "Cliente: Ana Torres" + SALTO
                        + "Monto solicitado: $9000.00" + SALTO
                        + "Capacidad máxima: $9600.00" + SALTO
                        + "Nivel de riesgo: BAJO" + SALTO
                        + "Resultado: APROBADA",
                resultado);
    }

    @Test
    public void debeEvaluarUnPrestamoAutomotrizAprobado() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoAutomotriz();

        String resultado = evaluador.evaluarSolicitud("Marta Díaz", 3500.0, 40000.0);

        assertEquals(
                "Producto: Préstamo automotriz" + SALTO
                        + "Cliente: Marta Díaz" + SALTO
                        + "Monto solicitado: $40000.00" + SALTO
                        + "Capacidad máxima: $42000.00" + SALTO
                        + "Nivel de riesgo: MEDIO" + SALTO
                        + "Resultado: APROBADA",
                resultado);
    }

    @Test
    public void debeEvaluarUnCreditoHipotecarioAprobado() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoHipotecario();

        String resultado = evaluador.evaluarSolicitud("Luis Herrera", 4800.0, 150000.0);

        assertEquals(
                "Producto: Crédito hipotecario" + SALTO
                        + "Cliente: Luis Herrera" + SALTO
                        + "Monto solicitado: $150000.00" + SALTO
                        + "Capacidad máxima: $172800.00" + SALTO
                        + "Nivel de riesgo: MEDIO" + SALTO
                        + "Resultado: APROBADA",
                resultado);
    }

    @Test
    public void debeRechazarSolicitudesConRiesgoAlto() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoPersonal();

        String resultado = evaluador.evaluarSolicitud("Carla Pérez", 2000.0, 12000.0);

        assertTrue(resultado.contains("Nivel de riesgo: ALTO"));
        assertTrue(resultado.endsWith("Resultado: RECHAZADA"));
    }

    @Test
    public void debeRechazarSolicitudesSinCliente() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoPersonal();

        try {
            evaluador.evaluarSolicitud(" ", 2000.0, 1000.0);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("El cliente es obligatorio", e.getMessage());
        }
    }

    @Test
    public void debeRechazarSolicitudesConMontosNoValidos() {
        EvaluadorCredito evaluador = new EvaluacionPrestamoAutomotriz();

        try {
            evaluador.evaluarSolicitud("Marta Díaz", 0.0, 1000.0);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Los montos deben ser mayores que cero", e.getMessage());
        }
    }

    @Test
    public void debeMostrarElEscenarioDeConsolaEnLaAplicacionPrincipal() {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try (PrintStream captura = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(captura);
            App.main(new String[0]);
        } finally {
            System.setOut(salidaOriginal);
        }

        String salida = buffer.toString(StandardCharsets.UTF_8);
        assertTrue(salida.contains("=== Simulación de evaluación de créditos ==="));
        assertTrue(salida.contains("Producto: Préstamo personal"));
        assertTrue(salida.contains("Cliente: Ana Torres"));
        assertTrue(salida.contains("Producto: Préstamo automotriz"));
        assertTrue(salida.contains("Cliente: Marta Díaz"));
        assertTrue(salida.contains("Producto: Crédito hipotecario"));
        assertTrue(salida.contains("Cliente: Luis Herrera"));
    }
}
