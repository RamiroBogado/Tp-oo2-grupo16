package negocio;

import dao.ProfesionalDao;
import modelo.Profesional;
import modelo.Especialidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Set;

public class ProfesionalAbm {
    private static ProfesionalAbm instancia = null;

    private ProfesionalAbm() {}

    public static ProfesionalAbm getInstance() {
        if (instancia == null) {
            instancia = new ProfesionalAbm();
        }
        return instancia;
    }

    // ALTA
    public int altaProfesional(String nombre, String apellido, String dni, 
                             String matricula, Set<Especialidad> especialidades) {
        
        validarDatosObligatorios(nombre, apellido, dni, matricula);
        
        if (existeProfesionalPorDni(dni)) {
            throw new RuntimeException("El profesional con DNI " + dni + " ya existe");
        }
        
        if (existeProfesionalPorMatricula(matricula)) {
            throw new RuntimeException("La matrícula " + matricula + " ya está registrada");
        }
        
        Profesional profesional = new Profesional(nombre, apellido, dni, matricula);
        profesional.setEspecialidades(especialidades);
        
        int id = ProfesionalDao.getInstance().agregar(profesional);
        
        System.out.println("Profesional creado con ID: " + id);
        
        return id;
    }

    // BAJAS
    public void eliminarProfesionalPorId(int id) {
        Profesional profesional = traerProfesional(id);
        if (profesional != null) {
            ProfesionalDao.getInstance().eliminar(profesional);
        } else {
            throw new RuntimeException("No existe profesional con ID: " + id);
        }
    }

    public void eliminarProfesionalPorDni(String dni) {
        Profesional profesional = traerProfesionalPorDni(dni);
        if (profesional != null) {
            ProfesionalDao.getInstance().eliminar(profesional);
            System.out.println("Profesional eliminado " + profesional);
        } else {
            throw new RuntimeException("No existe profesional con DNI: " + dni);
        }
    }

    // MODIFICACIONES
    public void modificarNombre(int id, String nuevoNombre) {
        Profesional profesional = traerProfesional(id);
        if (profesional != null) {
            String viejoNombre = profesional.getNombre();
            profesional.setNombre(nuevoNombre);
            ProfesionalDao.getInstance().actualizar(profesional);
            System.out.println("Profesional modificado de " + viejoNombre + " a " + nuevoNombre);
        } else {
            throw new RuntimeException("Profesional no encontrado");
        }
    }

    public void modificarApellido(int id, String nuevoApellido) {
        Profesional profesional = traerProfesional(id);
        if (profesional != null) {
            String viejoApellido = profesional.getApellido();
            profesional.setApellido(nuevoApellido);
            ProfesionalDao.getInstance().actualizar(profesional);
            System.out.println("Profesional modificado de " + viejoApellido + " a " + nuevoApellido);
        }
    }

    public void modificarMatricula(int id, String nuevaMatricula) {
        Profesional profesional = traerProfesional(id);
        if (profesional != null) {
            String viejaMatricula = profesional.getMatricula();
            profesional.setMatricula(nuevaMatricula);
            ProfesionalDao.getInstance().actualizar(profesional);
            System.out.println("Matrícula modificada de " + viejaMatricula + " a " + nuevaMatricula);
        }
    }

    public void agregarEspecialidad(int idProfesional, Especialidad especialidad) {
        Session session = dao.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Profesional profesional = session.get(Profesional.class, idProfesional);
            Especialidad espManaged = session.get(Especialidad.class, especialidad.getId_especialidad());
            
            if (profesional != null && espManaged != null) {
                profesional.getEspecialidades().add(espManaged);
                session.update(profesional);
                System.out.println("Especialidad " + espManaged.getNombre() + " agregada al profesional " + profesional.getNombre());
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void eliminarEspecialidad(int idProfesional, Especialidad especialidad) {
    	Session session = dao.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Profesional profesional = session.get(Profesional.class, idProfesional);
            Especialidad espManaged = session.get(Especialidad.class, especialidad.getId_especialidad());
            
            if (profesional != null && espManaged != null) {
                profesional.getEspecialidades().remove(espManaged);
                session.update(profesional);
                System.out.println("Especialidad " + espManaged.getNombre() + " removida del profesional " + profesional.getNombre());
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // CONSULTAS
    public Profesional traerProfesional(int id) {
        return ProfesionalDao.getInstance().traerProfesional(id);
    }

    public Profesional traerProfesionalConEspecialidades(int id) {
        return ProfesionalDao.getInstance().traerProfesionalConEspecialidades(id);
    }

    public Profesional traerProfesionalPorDni(String dni) {
        return ProfesionalDao.getInstance().traerProfesionalPorDni(dni);
    }

    public Profesional traerProfesionalPorMatricula(String matricula) {
        return ProfesionalDao.getInstance().traerProfesionalPorMatricula(matricula);
    }

    public List<Profesional> traerProfesionalesPorNombre(String nombre) {
        return ProfesionalDao.getInstance().traerProfesionalesPorNombre(nombre);
    }

    public List<Profesional> traerProfesionalesPorEspecialidad(String nombre) {
        return ProfesionalDao.getInstance().traerProfesionalesPorEspecialidad(nombre);
    }

    public List<Profesional> traerTodosProfesionales() {
        return ProfesionalDao.getInstance().traerTodos();
    }

    public List<Profesional> traerTodosProfesionalesConEspecialidades() {
        return ProfesionalDao.getInstance().traerTodosProfesionalesConEspecialidades();
    }

    // VALIDACIONES
    private boolean existeProfesionalPorDni(String dni) {
        return traerProfesionalPorDni(dni) != null;
    }

    private boolean existeProfesionalPorMatricula(String matricula) {
        return traerProfesionalPorMatricula(matricula) != null;
    }

    private void validarDatosObligatorios(String nombre, String apellido, String dni, 
                                       String matricula) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Apellido no puede estar vacío");
        }
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede estar vacío");
        }
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula no puede estar vacía");
        }
    }
}