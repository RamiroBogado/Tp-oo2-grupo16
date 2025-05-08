package negocio;

import dao.ContactoDao;
import modelo.Contacto;

public class ContactoAbm {

    private static ContactoAbm instancia = null; // Patrón Singleton

    private ContactoAbm() {
    }

    public static ContactoAbm getInstance() {
        if (instancia == null)
            instancia = new ContactoAbm();
        return instancia;
    }

    public void altaContacto(String mail, String numero) {
        Contacto contacto = new Contacto(mail, numero);
        
        if (traerContactoPorMail(contacto.getEmail()) == null) {
            ContactoDao.getInstance().agregar(contacto);
            System.out.println("El contacto con mail " + contacto.getEmail() + " fue dado de alta.");
        } else {
            System.out.println("El contacto con mail " + contacto.getEmail() + " ya existe.");
        }
    }

    public void bajaContacto(int id_contacto) {
        Contacto contacto = traerContacto(id_contacto);
        if (contacto != null) {
            ContactoDao.getInstance().eliminar(contacto);
        } else {
            System.out.println("No se encontró el contacto con ID: " + id_contacto);
        }
    }

    public Contacto traerContacto(int id_contacto) {
        return ContactoDao.getInstance().traerContacto(id_contacto);
    }

    public Contacto traerContactoPorMail(String email) {
        return ContactoDao.getInstance().traerContactoPorMail(email);
    }

}
