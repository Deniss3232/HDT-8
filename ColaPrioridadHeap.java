import java.util.ArrayList;

// Esta clase es una cola de prioridad usando heap para ordenar pacientes automáticamente
public class ColaPrioridadHeap {
    private ArrayList<FichaPaciente> elementos;

    public ColaPrioridadHeap() {
        elementos = new ArrayList<>();
    }

    // Agrega una ficha a la cola
    public void agregar(FichaPaciente paciente) {
        elementos.add(paciente);
        subir(elementos.size() - 1);
    }

    // Saca al paciente con mayor prioridad (A primero)
    public FichaPaciente siguiente() {
        if (elementos.isEmpty()) return null;

        FichaPaciente primero = elementos.get(0);
        FichaPaciente ultimo = elementos.remove(elementos.size() - 1);

        if (!elementos.isEmpty()) {
            elementos.set(0, ultimo);
            bajar(0);
        }

        return primero;
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    // Ordena hacia arriba si es necesario
    private void subir(int indice) {
        while (indice > 0) {
            int padre = (indice - 1) / 2;
            if (elementos.get(indice).compareTo(elementos.get(padre)) >= 0) break;
            intercambiar(indice, padre);
            indice = padre;
        }
    }

    // Ordena hacia abajo si es necesario
    private void bajar(int indice) {
        int hijoIzq = 2 * indice + 1;

        while (hijoIzq < elementos.size()) {
            int menor = hijoIzq;
            int hijoDer = hijoIzq + 1;

            if (hijoDer < elementos.size() && elementos.get(hijoDer).compareTo(elementos.get(hijoIzq)) < 0) {
                menor = hijoDer;
            }

            if (elementos.get(indice).compareTo(elementos.get(menor)) <= 0) break;

            intercambiar(indice, menor);
            indice = menor;
            hijoIzq = 2 * indice + 1;
        }
    }

    private void intercambiar(int i, int j) {
        FichaPaciente temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }
}
