package dao;

import modelo.Turno;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Date;

public class TurnoDao {
    private static TurnoDao instancia = null;

    private TurnoDao() {}

    public static TurnoDao getInstance() {
        if (instancia == null) {
            instancia = new TurnoDao();
        }
        return instancia;
    }

    // CREATE
    public int agregar(Turno turno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(turno);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return id;
    }

    // READ
    public Turno traerTurno(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Turno.class, id);
        } finally {
            session.close();
        }
    }

    public List<Turno> traerTurnosPorProfesionalCompletos(int idProfesional) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                "SELECT DISTINCT t FROM Turno t " +
                "LEFT JOIN FETCH t.profesional " +
                "LEFT JOIN FETCH t.cliente " +
                "LEFT JOIN FETCH t.servicio " +
                "WHERE t.profesional.id_persona = :idProf", Turno.class)
                .setParameter("idProf", idProfesional)
                .list();
        } finally {
            session.close();
        }
    }

    public List<Turno> traerTurnosPorFechaCompletos(Date fechaDesde, Date fechaHasta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                "SELECT DISTINCT t FROM Turno t " +
                "LEFT JOIN FETCH t.profesional " +
                "LEFT JOIN FETCH t.cliente " +
                "LEFT JOIN FETCH t.servicio " +
                "WHERE t.fecha_hora BETWEEN :fechaInicio AND :fechaFin", Turno.class)
                .setParameter("fechaInicio", fechaDesde)
                .setParameter("fechaFin", fechaHasta)
                .list();
        } finally {
            session.close();
        }
    }

    // UPDATE
    public void actualizar(Turno turno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(turno);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // DELETE
    public void eliminar(Turno turno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(turno);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}