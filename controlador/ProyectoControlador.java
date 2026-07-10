package controlador;

import excepciones.ProyectoException;
import modelo.Persona;
import modelo.Proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProyectoControlador {
    private static ProyectoControlador instance=null;
    private final List<Persona> personas;
    private final List<Proyecto> proyectos;

    private ProyectoControlador() {
        personas = new ArrayList<>();
        proyectos = new ArrayList<>();
    }

    public static ProyectoControlador getInstance() {
        if (instance == null) {
            instance = new ProyectoControlador();
        }
        return instance;
    }
    public String[][] listPersonasYSusTareasDeProyecto(int idProyecto) throws ProyectoException {

        Optional<Proyecto> proyectoOptional = findProyecto(idProyecto);

        if (proyectoOptional.isEmpty()) {
            throw new ProyectoException("No existe un proyecto con el id dado");
        }

        Proyecto proyecto = proyectoOptional.get();

        Persona[] personas = proyecto.getPersonasQueRealizanTareas();

        String[][] datos = new String[personas.length][5];

        int duracionTotalProyecto = proyecto.getDuracionTotalTareasDefinidas();

        for (int i = 0; i < personas.length; i++) {

            Persona persona = personas[i];

            int duracionPersona = persona.getDuracionTotalTareasRealizadas(proyecto);

            datos[i][0] = persona.getRut();

            datos[i][1] = persona.getNombre();
            int horas = duracionPersona / 60;
            int minutos = duracionPersona % 60;
            datos[i][2] = String.format("%02d:%02d", horas, minutos);
            double porcentaje = (double) duracionPersona * 100 / duracionTotalProyecto;
            datos[i][3] = String.format("%.2f%%", porcentaje);
            long monto = Math.round(proyecto.getMonto() * porcentaje / 100.0);
            datos[i][4] = String.valueOf(monto);
        }

        return datos;
    }
    public void createPersona(String rut, String nombre) throws ProyectoException {
        if (findPersona(rut).isEmpty()) {
            personas.add(new Persona(rut,nombre));
        } else throw new ProyectoException("Ya existe una persona con el rut dado");
    }

    public void createProyecto(int id, String detalle, long monto) throws ProyectoException {
        if (findProyecto(id).isEmpty()) {
            proyectos.add(new Proyecto(id,detalle,monto));
        } else throw new ProyectoException("Ya existe un proyecto con el id dado");
    }

    public void createTarea(int idTarea, String descripcion, int duracion, int idProyecto,
                            String rutPersona) throws ProyectoException {

        Optional<Persona> personaOptional = findPersona(rutPersona);
        Optional<Proyecto> proyectoOptional = findProyecto(idProyecto);
        if (personaOptional.isPresent()) {
            if (proyectoOptional.isPresent()) {
                if (!proyectoOptional.get().addTarea(idTarea, descripcion, duracion, personaOptional.get())) {
                    throw new ProyectoException("Ya existe una tarea en el proyecto con el id dado");
                }
            } else throw new ProyectoException("No existe un proyecto con el id dado");
        } else throw new ProyectoException("No existe una persona con el rut dado");
    }

    public void createDatosParaPrueba() {
        try {
            createPersona("11.111.111-1","Juan Salas");
            createPersona("22.222.222-2", "Laura Barra");
            createProyecto(10,"Sistema de Carga de Trabajo", 12600000L);
            createTarea(101,"Define Modelo Proceso",45,10,"22.222.222-2");
            createTarea(102,"Define MER",60,10,"11.111.111-1");
            createTarea(103,"Define Modelo Diagrama de Clases",45,10,"22.222.222-2");
        } catch (ProyectoException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Persona> findPersona(String rut) {
        return personas.stream().filter(persona -> persona.getRut().equalsIgnoreCase(rut)).findFirst();
    }

    private Optional<Proyecto> findProyecto(int id) {
        return proyectos.stream().filter(proyecto -> proyecto.getId()==id).findFirst();
    }
}
