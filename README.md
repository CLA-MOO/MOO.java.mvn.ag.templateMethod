
# Ejercicio: evaluación de créditos con Template Method

![credito-banca-comercial-ip](https://github.com/user-attachments/assets/ef793a23-cfc2-4175-b6aa-9a9c3b50a084)

Ejercicio en el dominio de **finanzas** para modelar la evaluación de solicitudes de crédito mediante el patrón de diseño **Template Method**.

La clase abstracta `EvaluadorCredito` define el template method `evaluarSolicitud(...)`, que fija el algoritmo general para:

1. validar los datos de entrada,
2. identificar el producto financiero,
3. calcular la capacidad máxima de endeudamiento,
4. evaluar el nivel de riesgo,
5. producir el resultado final de aprobación o rechazo.

Las clases concretas `EvaluacionPrestamoPersonal`, `EvaluacionPrestamoAutomotriz` y `EvaluacionPrestamoHipotecario` implementan los pasos variables del algoritmo, cambiando las reglas de capacidad y riesgo según el tipo de crédito. El programa principal ejecuta los tres casos para mostrar el comportamiento del patrón en consola.

### Escenario de ejecución en consola del programa principal

```text
=== Simulación de evaluación de créditos ===
Producto: Préstamo personal
Cliente: Ana Torres
Monto solicitado: $9000.00
Capacidad máxima: $9600.00
Nivel de riesgo: BAJO
Resultado: APROBADA

Producto: Préstamo automotriz
Cliente: Marta Díaz
Monto solicitado: $40000.00
Capacidad máxima: $42000.00
Nivel de riesgo: MEDIO
Resultado: APROBADA

Producto: Crédito hipotecario
Cliente: Luis Herrera
Monto solicitado: $150000.00
Capacidad máxima: $172800.00
Nivel de riesgo: MEDIO
Resultado: APROBADA
```

## Diagrama de clases
[Editor en línea](https://mermaid.live/)
```mermaid
---
title: Template Method - Evaluación de créditos
---
classDiagram
      class EvaluadorCredito {
        <<abstract>>
        +evaluarSolicitud(cliente, ingresosMensuales, montoSolicitado) String
        #obtenerProducto() String
        #calcularCapacidadMaxima(ingresosMensuales) double
        #evaluarRiesgo(ingresosMensuales, montoSolicitado) String
      }

      class EvaluacionPrestamoPersonal {
        +obtenerProducto() String
        +calcularCapacidadMaxima(ingresosMensuales) double
        +evaluarRiesgo(ingresosMensuales, montoSolicitado) String
      }

      class EvaluacionPrestamoAutomotriz {
        +obtenerProducto() String
        +calcularCapacidadMaxima(ingresosMensuales) double
        +evaluarRiesgo(ingresosMensuales, montoSolicitado) String
      }

      class EvaluacionPrestamoHipotecario {
        +obtenerProducto() String
        +calcularCapacidadMaxima(ingresosMensuales) double
        +evaluarRiesgo(ingresosMensuales, montoSolicitado) String
      }

      class App {
        +main(args) void
      }

      EvaluadorCredito <|-- EvaluacionPrestamoPersonal
      EvaluadorCredito <|-- EvaluacionPrestamoAutomotriz
      EvaluadorCredito <|-- EvaluacionPrestamoHipotecario
      App --> EvaluacionPrestamoPersonal
      App --> EvaluacionPrestamoAutomotriz
      App --> EvaluacionPrestamoHipotecario
```
[Referencia-Mermaid](https://mermaid.js.org/syntax/classDiagram.html)

## Diagrama de clases UML con draw.io

El repositorio está configurado para crear Diagramas de clases UML con ```draw.io```. Sigue estos pasos para usarlo:

1. Haz doble clic sobre el archivo ```uml.class.drawio.png``` en el explorador de archivos.
2. Se abrirá el editor de ```draw.io``` integrado en el entorno.
3. En la barra lateral izquierda, haz clic en ```+Más formas```.
4. En el cuadro de diálogo, busca y activa la categoría **UML** y haz clic en ```Aceptar```.
5. Las formas UML estarán disponibles en el panel izquierdo para arrastrarlas al lienzo.
6. Diseña tu diagrama de clases UML agregando clases, atributos, métodos y relaciones.
7. Guarda los cambios con ```Ctrl+S``` (o ```Cmd+S``` en Mac). El archivo ```.png``` se actualizará automáticamente.

### Prompts para generar los Diagramas de Clase y Secuencia con MermAId

Para mejores resultados sigue estos pasos:

1. Abre el chat de GitHub Copilot en tu entorno de desarrollo.
2. Agrega como contexto las clases del proyecto (por ejemplo, arrastra los archivos `.java` al chat o menciónalos con `#`).
3. Aplica el prompt para el **Diagrama de Clases UML**:

```
@mermaid /uml
```

4. Revisa el diagrama generado en la vista previa de Mermaid.
5. Si también necesitas un **Diagrama de Secuencia**, aplica el siguiente prompt (manteniendo el mismo contexto):

```
@mermaid /sequence
```

6. Copia el código Mermaid generado y pégalo en la sección correspondiente del ```README.md``` o en [el editor en línea](https://mermaid.live/) para visualizarlo.

## Versión de Java

Verifica que tengas la versión adecuada de Java para trabajar con Maven. En caso de requerir una versión especial, usa los siguientes comandos.

### Verificar versión actual
```
java --version
```
### Verificar versiones disponibles para instalar
```
sdk list java
```
### Instalar la última versión
```
sdk install java
```
### Instalar una versión específica
```
sdk install java xxx-version
```
Ejemplo:
```
sdk install java 17.0.18-ms
```
## Uso del proyecto con Maven

### Compilar
```
mvn compile
```
### Probar N tests
```
mvn test
```
### Probar individualmente las pruebas del ejercicio
```
mvn test -Dtest="AppTest#debeEvaluarUnPrestamoPersonalAprobado"
mvn test -Dtest="AppTest#debeEvaluarUnPrestamoAutomotrizAprobado"
mvn test -Dtest="AppTest#debeEvaluarUnCreditoHipotecarioAprobado"
mvn test -Dtest="AppTest#debeRechazarSolicitudesConRiesgoAlto"
mvn test -Dtest="AppTest#debeRechazarSolicitudesSinCliente"
mvn test -Dtest="AppTest#debeRechazarSolicitudesConMontosNoValidos"
mvn test -Dtest="AppTest#debeMostrarElEscenarioDeConsolaEnLaAplicacionPrincipal"
```
### Ejecutar App
```
mvn -q exec:java
```
```
java -cp target/classes miPrincipal.App
```
### Empacar App
```
mvn package
```
### Limpiar binarios
```
mvn clean
```
## Comandos Git-Cambios y envío a Autograding

### Por cada cambio importante que haga, actualice su historia usando los comandos:
```
git add .
git commit -m "Descripción del cambio"
```
### Envíe sus actualizaciones a GitHub para Autograding con el comando:
```
git push origin main
```
