package modelo;

public class Tarea {
    private int id;
    private String descripcion;
    private int duracion;
    private final Persona persona;
    private final Proyecto proyecto;

    public Tarea(int id, String descripcion, int duracion, Proyecto proyecto, Persona persona) {
        this.id = id;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.persona = persona;
        this.persona.addTarea(this);
        this.proyecto = proyecto;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Persona getPersona() {
        return persona;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }
}
