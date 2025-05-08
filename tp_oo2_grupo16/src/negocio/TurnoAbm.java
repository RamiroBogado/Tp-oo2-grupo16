package negocio;

import dao.TurnoDao;
import modelo.Turno;
import modelo.Profesional;
import modelo.Cliente;
import modelo.Servicio;
import java.util.Date;
import java.util.List;

public class TurnoAbm {
    private static TurnoAbm instancia = null;

    private TurnoAbm() {}

    public static TurnoAbm getInstance() {
        if (instancia == null) {
            instancia = new TurnoAbm();
        }
        return instancia;
    }

    // ALTA
    public int altaTurno(Profesional profesional, Cliente cliente, Servicio servicio, 
                        Date fechaHora, String observaciones, String estado) {
        validarDatos(profesional, cliente, servicio, fechaHora);
        
        Turno turno = new Turno();
        turno.setProfesional(profesional);
        turno.setCliente(cliente);
        turno.setServicio(servicio);
        turno.setFecha_hora(fechaHora);
        turno.setObservaciones(observaciones);
        turno.setEstado(estado);
        
        return TurnoDao.getInstance().agregar(turno);
    }

    // BAJA
    public void eliminarTurno(int id) {
        Turno turno = traerTurno(id);
        if (turno != null) {
            TurnoDao.getInstance().eliminar(turno);
        }
    }

    // MODIFICACIÓN
    public void modificarEstado(int id, String nuevoEstado) {
        Turno turno = traerTurno(id);
        if (turno != null) {
            turno.setEstado(nuevoEstado);
            TurnoDao.getInstance().actualizar(turno);
        }
    }

    // CONSULTAS
    public Turno traerTurno(int id) {
        return TurnoDao.getInstance().traerTurno(id);
    }

    public List<Turno> traerTurnosPorProfesionalCompletos(int idProfesional) {
        return TurnoDao.getInstance().traerTurnosPorProfesionalCompletos(idProfesional);
    }

    public List<Turno> traerTurnosPorFechaCompletos(Date fechaDesde, Date fechaHasta) {
        return TurnoDao.getInstance().traerTurnosPorFechaCompletos(fechaDesde, fechaHasta);
    }

    // VALIDACIÓN
    private void validarDatos(Profesional profesional, Cliente cliente, 
                            Servicio servicio, Date fechaHora) {
        if (profesional == null) {
            throw new IllegalArgumentException("El profesional no puede ser nulo");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }
        if (fechaHora == null || fechaHora.before(new Date())) {
            throw new IllegalArgumentException("La fecha/hora no puede ser nula o anterior a la actual");
        }
    }
}