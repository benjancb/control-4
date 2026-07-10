package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Proyecto {
    private int id;
    private String detalle;
    private long monto;
    private final List<Tarea> tareasDefinidas;

    public Proyecto(int id, String detalle, long monto) {
        this.id = id;
        this.detalle = detalle;
        this.monto = monto;
        tareasDefinidas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public long getMonto() {
        return monto;
    }

    public boolean addTarea(int id,String descripcion,int duracion,Persona persona) {
        if (findTarea(id).isEmpty()) {
            tareasDefinidas.add(new Tarea(id,descripcion,duracion,this,persona));
            return true;
        }
        return false;
    }

    public int getDuracionTotalTareasDefinidas() {
        int duraciontotal=0;
        for (Tarea tarea: tareasDefinidas) {
            duraciontotal += tarea.getDuracion();
        }
        return duraciontotal;
    }

    public Persona[] getPersonasQueRealizanTareas() {
        List<Persona> personasDelProyecto = new ArrayList<>();
        for (Tarea tarea : tareasDefinidas) {
            if (!personasDelProyecto.contains(tarea.getPersona())) {
                personasDelProyecto.add(tarea.getPersona());
            }
        }
        return personasDelProyecto.toArray(new Persona[0]);
    }

    private Optional<Tarea> findTarea(int id) {
        return tareasDefinidas.stream().filter(tarea -> tarea.getId()==id).findFirst();
    }
}
