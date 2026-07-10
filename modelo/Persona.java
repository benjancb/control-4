package modelo;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String rut;
    private String nombre;
    private final List<Tarea> tareasRealizadas;

    public Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
        tareasRealizadas = new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void addTarea(Tarea tarea) {
        if (!tareasRealizadas.contains(tarea)) {
            tareasRealizadas.add(tarea);
        }
    }

    public int getDuracionTotalTareasRealizadas(Proyecto proyecto) {
        int duracionTotal=0;
        for (Tarea tarea : tareasRealizadas) {
            if (tarea.getProyecto() == proyecto) {
                duracionTotal += tarea.getDuracion();
            }
        }
        return duracionTotal;
    }
}
