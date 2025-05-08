package modelo;

import java.util.Set;
import java.util.HashSet;

public class Servicio {
	
    private int id_servicio;
    private String nombre;
    private String descripcion;
    private Set<Turno> turnos = new HashSet<>();
    
    public Servicio() {}

	public Servicio(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;

	}

	public int getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(int id_servicio) {
		this.id_servicio = id_servicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(Set<Turno> turnos) {
		this.turnos = turnos;
	}

	@Override
	public String toString() {
		return "Servicio [id_servicio=" + id_servicio + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
    
    
}