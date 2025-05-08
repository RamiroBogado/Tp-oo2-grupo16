package dao;

import modelo.Profesional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ProfesionalDao {
    private static ProfesionalDao instancia = null;

    private ProfesionalDao() {}

    public static ProfesionalDao getInstance() {
        if (instancia == null) {
            instancia = new ProfesionalDao();
        }
        return instancia;
    }

    // CREATE
    public int agregar(Profesional profesional) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(profesional);
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
    public Profesional traerProfesional(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Profesional.class, id);
        } finally {
            session.close();
        }
    }

    public Profesional traerProfesionalConEspecialidades(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                "SELECT DISTINCT p FROM Profesional p " +
                "LEFT JOIN FETCH p.especialidades " +
                "WHERE p.id_persona = :id", Profesional.class)
                .setParameter("id", id)
                .uniqueResult();
        } finally {
            session.close();
        }
    }

    public Profesional traerProfesionalPorDni(String dni) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Profesional> query = session.createQuery(
                "FROM Profesional p WHERE p.dni = :dni", Profesional.class);
            query.setParameter("dni", dni);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public Profesional traerProfesionalPorMatricula(String matricula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Profesional> query = session.createQuery(
                "FROM Profesional p WHERE p.matricula = :matricula", Profesional.class);
            query.setParameter("matricula", matricula);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<Profesional> traerProfesionalesPorNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Profesional> query = session.createQuery(
                "FROM Profesional p WHERE p.nombre LIKE :nombre", Profesional.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.list();
        } finally {
            session.close();
        }
    }

    public List<Profesional> traerProfesionalesPorEspecialidad(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Profesional> query = session.createQuery(
                "SELECT DISTINCT p FROM Profesional p " +
                "LEFT JOIN FETCH p.especialidades e " +
                "WHERE e.nombre = :nombre", Profesional.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } finally {
            session.close();
        }
    }

    public List<Profesional> traerTodosProfesionalesConEspecialidades() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                "SELECT DISTINCT p FROM Profesional p " +
                "LEFT JOIN FETCH p.especialidades", Profesional.class)
                .getResultList();
        } finally {
            session.close();
        }
    }

    public List<Profesional> traerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Profesional> query = session.createQuery(
                "FROM Profesional", Profesional.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    // UPDATE
    public void actualizar(Profesional profesional) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(profesional);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // DELETE
    public void eliminar(Profesional profesional) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(profesional);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}