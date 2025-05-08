package negocio;

import dao.ServicioDao;
import modelo.Servicio;
import java.util.List;

public class ServicioAbm {
    private static ServicioAbm instancia = null;

    private ServicioAbm() {}

    public static ServicioAbm getInstance() {
        if (instancia == null) {
            instancia = new ServicioAbm();
        }
        return instancia;
    }

    // ALTA
    public int altaServicio(String nombre, String descripcion) {
        validarDatos(nombre);
        
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        
        return ServicioDao.getInstance().agregar(servicio);
    }

    // BAJA
    public void eliminarServicio(int id) {
        Servicio servicio = traerServicio(id);
        if (servicio != null) {
            ServicioDao.getInstance().eliminar(servicio);
        }
    }

    // MODIFICACIÓN
    public void modificarServicio(int id, String nuevoNombre, String nuevaDescripcion) {
        Servicio servicio = traerServicio(id);
        if (servicio != null) {
            servicio.setNombre(nuevoNombre);
            servicio.setDescripcion(nuevaDescripcion);
            ServicioDao.getInstance().actualizar(servicio);
        }
    }

    // CONSULTAS
    public Servicio traerServicio(int id) {
        return ServicioDao.getInstance().traerServicio(id);
    }

    public List<Servicio> traerTodosServicios() {
        return ServicioDao.getInstance().traerTodos();
    }

    // VALIDACIÓN
    private void validarDatos(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío");
        }
    }
}