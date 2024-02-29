package com.example.exament3ejercicio2;

import javafx.collections.ListChangeListener;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloaderAndZipper implements ListChangeListener<UrlInfo> {
    @Override
    public void onChanged(Change<? extends UrlInfo> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (UrlInfo addedUrlInfo : change.getAddedSubList()) {
                    String url = addedUrlInfo.getUrl();
                    String uniqueString = addedUrlInfo.getUniqueString();

                    if (url.isEmpty()) {
                    CompletableFuture.supplyAsync(() -> downloadWebPage(url))
                            .thenAccept(content -> saveToFile(url, content))
                            .thenRun(DownloaderAndZipper::compressFiles);
                    } else {
                        System.out.println(url + " encolado como " + uniqueString);
                    }
                }
            }
        }
    }

    private static String downloadWebPage(String urlString) {
        try {
            // Crea un cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construye la solicitud GET para la URL proporcionada
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            // Envía la solicitud y obtiene la respuesta como una cadena de texto
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // Retorna el contenido de la página web
        } catch (IOException | InterruptedException e) {
            // Maneja posibles errores de la operación de descarga
            System.err.println("Error al descargar la página: " + e.getMessage());
            return null;
        }
    }

    // Método para guardar el contenido en un archivo dado el nombre de la URL y el contenido
    private static void saveToFile(String url, String content) {
        try {
            // Define la ruta del directorio donde se guardarán los archivos
            Path directoryPath = Path.of("src", "main", "resources", "urls");

            // Construye el nombre del archivo reemplazando caracteres no permitidos
            String fileName = url.replaceAll("[^a-zA-Z0-9.-]", "_") + ".html";

            // Resuelve la ruta completa del archivo dentro del directorio especificado
            Path filePath = directoryPath.resolve(fileName);

            // Crea los directorios si no existen
            Files.createDirectories(directoryPath);

            // Utiliza un BufferedWriter para escribir el contenido en el archivo
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
                writer.write(content);
            }

        } catch (IOException e) {
            // Maneja posibles errores de la operación de guardado
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private static void compressFiles() {
        try {
            // Directorio donde se encuentran los archivos txt
            Path sourceDirectory = Path.of("src", "main", "resources", "urls");

            // Lista de archivos txt en el directorio
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDirectory, "*.html")) {
                // Flujo de salida para el archivo ZIP
                try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("src/main/resources/archivos_comprimidos.zip"))) {
                    // Itera sobre los archivos txt y los añade al archivo ZIP
                    for (Path filePath : directoryStream) {
                        String fileName = filePath.getFileName().toString();
                        zipOutputStream.putNextEntry(new ZipEntry(fileName));

                        // Lee el contenido del archivo y lo escribe en el archivo ZIP
                        byte[] content = Files.readAllBytes(filePath);
                        zipOutputStream.write(content);

                        zipOutputStream.closeEntry();
                    }
                }
            }

            System.out.println("Compresión completada. Archivo ZIP creado en: src/main/resources/archivos_comprimidos.zip");

        } catch (IOException e) {
            // Maneja posibles errores de la operación de compresión
            System.err.println("Error durante la compresión: " + e.getMessage());
        }
    }
}

