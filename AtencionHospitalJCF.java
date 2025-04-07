import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

// Este programa usa la cola con prioridad incluida en Java (PriorityQueue)
public class AtencionHospitalJCF {
    public static void main(String[] args) {
        PriorityQueue<FichaPaciente> colaPacientes = new PriorityQueue<>();

        try {
            File archivo = new File("pacientes.txt");
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(",");

                String nombre = datos[0].trim();
                String sintomas = datos[1].trim();
                char prioridad = datos[2].trim().charAt(0);

                FichaPaciente nuevo = new FichaPaciente(nombre, sintomas, prioridad);
                colaPacientes.add(nuevo);
            }

            lector.close();

            System.out.println("Pacientes en el orden que deben ser atendidos:");
            while (!colaPacientes.isEmpty()) {
                System.out.println(colaPacientes.poll());
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}
