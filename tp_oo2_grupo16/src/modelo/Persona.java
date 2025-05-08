package modelo;

public abstract class Persona {
    private int id_persona;
    private String nombre;
    private String apellido;
    private String dni;

    public Persona() {}

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    // Getters y setters
    public int getId_persona() {
        return id_persona;
    }

    protected void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "_Persona id=" + id_persona + ", nombre=" + nombre + 
               ", apellido=" + apellido + ", dni=" + dni;
    }
}