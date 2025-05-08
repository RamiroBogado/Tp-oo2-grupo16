package dao;

import modelo.Especialidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EspecialidadDao {
    private static EspecialidadDao instancia = null;

    private EspecialidadDao() {}

    public static EspecialidadDao getInstance() {
        if (instancia == null) {
            instancia = new EspecialidadDao();
        }
        return instancia;
    }

    // CREATE
    public int agregar(Especialidad especialidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(especialidad);
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
    public Especialidad traerEspecialidad(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Especialidad.class, id);
        } finally {
            session.close();
        }
    }

    public Especialidad traerEspecialidadPorNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Especialidad> query = session.createQuery(
                "FROM Especialidad e WHERE e.nombre = :nombre", Especialidad.class);
            query.setParameter("nombre", nombre);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public List<Especialidad> traerEspecialidadesPorDescripcion(String descripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Especialidad> query = session.createQuery(
                "FROM Especialidad e WHERE e.descripcion LIKE :descripcion", Especialidad.class);
            query.setParameter("descripcion", "%" + descripcion + "%");
            return query.list();
        } finally {
            session.close();
        }
    }

    public List<Especialidad> traerTodas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Especialidad> query = session.createQuery(
                "FROM Especialidad", Especialidad.class);
            return query.list();
        } finally {
            session.close();
        }
    }
    
    public List<Especialidad> traerEspecialidades() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Especialidad", Especialidad.class).list();
        } finally {
            session.close();
        }
    }

    // UPDATE
    public void actualizar(Especialidad especialidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(especialidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // DELETE
    public void eliminar(Especialidad especialidad) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(especialidad);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}