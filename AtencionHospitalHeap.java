import java.io.File;
import java.util.Scanner;

// Este programa usa tu propia cola con prioridad para atender pacientes
public class AtencionHospitalHeap {
    public static void main(String[] args) {
        ColaPrioridadHeap colaPacientes = new ColaPrioridadHeap();

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
                colaPacientes.agregar(nuevo);
            }

            lector.close();

            System.out.println("Pacientes en el orden que deben ser atendidos:");
            while (!colaPacientes.estaVacia()) {
                System.out.println(colaPacientes.siguiente());
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}
