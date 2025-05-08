package negocio;

import dao.EspecialidadDao;
import modelo.Especialidad;

public class EspecialidadAbm {

    private static EspecialidadAbm instancia = null; // Patrón Singleton

    private EspecialidadAbm() {
    }

    public static EspecialidadAbm getInstance() {
        if (instancia == null)
            instancia = new EspecialidadAbm();
        return instancia;
    }

    public void altaEspecialidad(String nombre, String descripcion) {
        Especialidad especialidad = new Especialidad(nombre, descripcion);
        
        if (traerEspecialidadPorNombre(especialidad.getNombre()) == null) {
            EspecialidadDao.getInstance().agregar(especialidad);
            System.out.println("La especialidad " + especialidad.getNombre() + " fue dada de alta.");
        } else {
            System.out.println("La especialidad " + especialidad.getNombre() + " ya existe.");
        }
    }

    // Método que verifica si ya existe una especialidad por nombre
    public Especialidad traerEspecialidadPorNombre(String nombre) {
        return EspecialidadDao.getInstance().traerEspecialidadPorNombre(nombre);
    }

    public void listarEspecialidades() {
        EspecialidadDao.getInstance().traerEspecialidades().forEach(especialidad -> {
            System.out.println("ID: " + especialidad.getId_especialidad() + ", Nombre: " + especialidad.getNombre() + ", Descripción: " + especialidad.getDescripcion());
        });
    }
}
