package dao;

import modelo.Cliente;
import org.hibernate.Session;
import java.util.List;

public class ClienteDao {
    private static ClienteDao instancia = null;

    private ClienteDao() {}

    public static ClienteDao getInstance() {
        if (instancia == null) {
            instancia = new ClienteDao();
        }
        return instancia;
    }

    // Operaciones CRUD
    public int agregar(Cliente cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            int id = (int) session.save(cliente);
            session.getTransaction().commit();
            return id;
        }
    }

    public void actualizar(Cliente cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(cliente);
            session.getTransaction().commit();
        }
    }

    public void eliminar(Cliente cliente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(cliente);
            session.getTransaction().commit();
        }
    }

    // Consultas
    public Cliente traerCliente(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, id);
        }
    }

    public Cliente traerClientePorDni(String dni) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente WHERE dni = :dni", Cliente.class)
                         .setParameter("dni", dni)
                         .uniqueResult();
        }
    }

    public List<Cliente> traerClientesPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente WHERE nombre LIKE :nombre", Cliente.class)
                         .setParameter("nombre", "%" + nombre + "%")
                         .list();
        }
    }

    public List<Cliente> traerClientesConcurrentes(boolean concurrente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente WHERE concurrente = :concurrente", Cliente.class)
                         .setParameter("concurrente", concurrente)
                         .list();
        }
    }

    public List<Cliente> traerTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente", Cliente.class).list();
        }
    }
}