package modelo;

public class Cliente extends Persona {
    private boolean concurrente;
    private Contacto contacto;

    public Cliente() {}

    public Cliente(String nombre, String apellido, String dni, boolean concurrente, Contacto contacto) {
        super(nombre, apellido, dni);
        
        this.concurrente = concurrente;
        this.setContacto(contacto); // Usamos setter para bidireccionalidad
    }

    // Getters y setters
    public boolean isConcurrente() {
        return concurrente;
    }

    public void setConcurrente(boolean concurrente) {
        this.concurrente = concurrente;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
        if(contacto != null && contacto.getCliente() != this) {
            contacto.setCliente(this); // Establece relación inversa
        }
    }

    @Override
    public String toString() {
        return "Cliente" + super.toString() + ", concurrente=" + concurrente + 
               ", " + contacto;
    }
}