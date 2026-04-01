
# 🛒 Reto de Automatización QA FrontEnd - Sauce Demo

Este repositorio contiene una suite de pruebas automatizadas para la plataforma **Sauce Demo (https://www.saucedemo.com/)**, desarrollada como solución a un reto técnico. Se utiliza **Java** con **Playwright** y **Cucumber**, implementando una arquitectura robusta basada en el patrón de diseño **Page Object Model (POM)**.

## 🎯 Objetivos del Proyecto

-   **Automatizar** los flujos críticos de la Historia de Usuario: Login, Agregar Productos, Revisar Carrito y Comprar.

-   **Implementar** una estructura mantenible y escalable.

-   **Garantizar** la recolección de evidencias (Capturas de pantalla, Videos y Traces).



## 🚀 Configuración y Ejecución

### 1. Requisitos Previos

-   **Java JDK 17** o superior.

-   **Maven** instalado y configurado en las variables de entorno.


### 2. Configuración de Credenciales (CRÍTICO)

1. Clonar repositorio
```
git clone https://github.com/MBGG8/reto-qa-frontend.git
cd reto-qa-frontend
```
2. Instalar dependencias
```
mvn clean install
```
Por seguridad, las credenciales y la URL base no están _hardcodeadas_ en el código. **Es obligatorio** crear el archivo de configuración local antes de ejecutar las pruebas:

1.  Dirígete a la ruta: `src/test/resources/`

2.  Crea un archivo llamado: `config.properties`

3.  Agrega el siguiente contenido:


Properties

```
BASE_URL=https://www.saucedemo.com/
PASSWORD=secret_sauce

```

> **Nota:** Este archivo debe estar incluido en el `.gitignore` para evitar la exposición de secretos en el repositorio remoto.

### 3. Ejecución desde la Terminal

Para ejecutar toda la suite de pruebas y generar reportes, utiliza el siguiente comando:

Bash

```
mvn clean test

```

## 📊 Reportes y Evidencias

El framework genera automáticamente evidencias de nivel profesional en la carpeta `target/`:

-   **Reporte HTML:** Se genera en `target/cucumber-report.html`.

-   **Videos:** Cada ejecución graba un video del flujo en `target/videos/`.

-   **Tracing:** En caso de fallo, se genera un archivo de traza en `target/traces/`. Puedes visualizar el error paso a paso con el visualizador de Playwright:


Bash

```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace target/traces/nombre-del-archivo.zip"

```

## 📝 Escenarios Cubiertos

-   **Login:** Verificación de acceso para `standard_user` y validación de error para `locked_out_user`.

-   **Carrito:** Flujo de agregar productos y verificación de persistencia en el carrito.

-   **Compra:** Proceso completo de checkout (ingreso de datos de envío) hasta la confirmación de orden exitosa.


----------

**Repositorio:** [reto-qa-frontend](https://github.com/MBGG8/reto-qa-frontend.git)

**Autor:** Miguel Gutierrez