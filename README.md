# Aplicación de Descarga y Compresión de URLs

Esta aplicación Java proporciona una funcionalidad para descargar y almacenar contenido de páginas web asociado a URLs introducidas por el usuario. Además, incluye una característica que permite la compresión de los archivos descargados en un único archivo ZIP.

## Funcionalidades

1. **Descarga de URLs:**
   - Cuando el usuario introduce una URL, la aplicación la descarga y almacena en un archivo local.
   - Cada archivo se guarda con un nombre único basado en una cadena aleatoria generada.

2. **Compresión de Archivos:**
   - Cuando el usuario introduce una URL vacía, la aplicación inicia el proceso de descarga de todas las URLs introducidas anteriormente.
   - Una vez que todas las descargas se completan, se procede a la compresión de los archivos descargados en un único archivo ZIP.
   - La compresión se realiza utilizando el concepto de Futuros de Java.

## Requisitos del Sistema

- **Java:** Asegúrate de tener Java instalado en tu sistema.
- **Entorno de Desarrollo:** Se recomienda utilizar un entorno de desarrollo integrado (IDE) compatible con Java, como IntelliJ IDEA o Eclipse.

## Instrucciones de Uso
 **Clonar el Repositorio:**
   ```bash
   git clone https://tu-repositorio.git
   cd tu-proyecto
