/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package miPrincipal;

import org.junit.Test;

import motores.*;

import static org.junit.Assert.*;

public class AppTest {
    @Test public void testDiagMotorComun() {
        Motor m = new MotorComun();
        assertTrue("Encendiendo Motor Comun+Acelerando Motor Comun+Apagando Motor Comun".equals(m.diagnosticar()));
    }

    @Test public void testDiagMotorEconomico() {
        Motor m = new MotorEconomico();
        assertTrue("Encendiendo Motor Economico+Apagando Motor Economico".equals(m.diagnosticar()));
    }

    @Test public void testDiagMotorElectrico() {
        Motor m = new MotorElectricoAdapter();
        assertTrue("Conectando y Activando Motor Electrico+Conectando y Activando Motor Electrico+Aumentando Velocidad Motor Electrico+Deteniendo Motor Electrico".equals(m.diagnosticar()));
    }

    @Test public void testDiagMotorHibrido() {
        Motor m = new MotorHibridoAdapter();
        assertTrue("Encendiendo Motor Hibrido+Acelerando Motor Hibrido+Acelerando Motor Hibrido+Deteniendo Motor Hibrido".equals(m.diagnosticar()));
    }
}
