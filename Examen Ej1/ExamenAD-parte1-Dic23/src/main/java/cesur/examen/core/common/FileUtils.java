package cesur.examen.core.common;

import cesur.examen.core.worker.Worker;
import lombok.extern.java.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: José Miguel Ruiz Guevara
 * Fecha: 11/12/2023
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */

@Log
public class FileUtils {

    public static void toCSV(String fileName, List<Worker> workers) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        if (workers == null) {
            throw new IllegalArgumentException("Workers list cannot be null");
        }

        log.info("Writing to CSV file: " + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ID,Nombre,DNI,Desde\n");

            for (Worker worker : workers) {
                String line = String.format("%d,%s,%s,%s\n",
                        worker.getId(),
                        worker.getName(),
                        worker.getDni(),
                        worker.getFrom().toString());
                writer.write(line);

                log.info("Writing line: " + line);
            }

            log.info("CSV file written successfully");

        } catch (IOException e) {
            log.severe("Error writing to CSV file: " + e.getMessage());
            throw new RuntimeException("Error writing to CSV file: " + e.getMessage(), e);
        }
    }
}