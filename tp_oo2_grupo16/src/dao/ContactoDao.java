package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Contacto;

public class ContactoDao {

    private static Session session;
    private Transaction tx;
    private static ContactoDao instancia = null; // Patrón Singleton

    protected ContactoDao() {}

    public static ContactoDao getInstance() {
        if (instancia == null)
            instancia = new ContactoDao();
        return instancia;
    }

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public int agregar(Contacto objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return id;
    }
    
    public void actualizar(Contacto objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public void eliminar(Contacto objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public Contacto traerContacto(int id_contacto) {
        Contacto objeto = null;
        try {
            iniciaOperacion();
            objeto = (Contacto) session.get(Contacto.class, id_contacto);
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public Contacto traerContactoPorMail(String email) {
        Contacto objeto = null;
        try {
            iniciaOperacion();
            objeto = (Contacto) session.get(Contacto.class, email);
        } finally {
            session.close();
        }
        return objeto;
    }
}
