package modelo;

public class Contacto {
    private int id_contacto;
    private String telefono;
    private String email;
    private Cliente cliente;

    public Contacto() {}

    public Contacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters
    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if(cliente != null && cliente.getContacto() != this) {
            cliente.setContacto(this); // Mantiene consistencia bidireccional
        }
    }

    @Override
    public String toString() {
        return "Contacto id=" + id_contacto + ", telefono=" + telefono + 
               ", email=" + email;
    }
}