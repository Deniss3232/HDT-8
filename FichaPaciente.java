// Esta clase representa la ficha del paciente
// También permite comparar pacientes según su prioridad (A antes que B, etc.)
public class FichaPaciente implements Comparable<FichaPaciente> {
    private String nombre;
    private String sintomas;
    private char prioridad; // A = muy urgente, E = menos urgente

    public FichaPaciente(String nombre, String sintomas, char prioridad) {
        this.nombre = nombre;
        this.sintomas = sintomas;
        this.prioridad = prioridad;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerSintomas() {
        return sintomas;
    }

    public char obtenerPrioridad() {
        return prioridad;
    }

    // Compara dos pacientes por su prioridad (A tiene más prioridad que B, etc.)
    @Override
    public int compareTo(FichaPaciente otro) {
        return Character.compare(this.prioridad, otro.prioridad);
    }

    // Muestra la ficha de forma entendible
    @Override
    public String toString() {
        return nombre + ", " + sintomas + ", " + prioridad;
    }
}
