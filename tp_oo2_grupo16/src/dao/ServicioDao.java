package dao;

import modelo.Servicio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ServicioDao {
    private static ServicioDao instancia = null;

    private ServicioDao() {}

    public static ServicioDao getInstance() {
        if (instancia == null) {
            instancia = new ServicioDao();
        }
        return instancia;
    }

    // CREATE
    public int agregar(Servicio servicio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(servicio);
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
    public Servicio traerServicio(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Servicio.class, id);
        } finally {
            session.close();
        }
    }

    public List<Servicio> traerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Servicio> query = session.createQuery("FROM Servicio", Servicio.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    // UPDATE
    public void actualizar(Servicio servicio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(servicio);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    // DELETE
    public void eliminar(Servicio servicio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(servicio);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}