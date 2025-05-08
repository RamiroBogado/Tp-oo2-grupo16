package modelo;

import java.util.Set;
import java.util.HashSet;

public class Especialidad {
	
    private int id_especialidad;
    private String nombre;
    private String descripcion;
    private Set<Profesional> profesionales = new HashSet<>();

    public Especialidad() {}

	public Especialidad(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getId_especialidad() {
		return id_especialidad;
	}

	public void setId_especialidad(int id_especialidad) {
		this.id_especialidad = id_especialidad;
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
	
	public Set<Profesional> getProfesionales() {
		return profesionales;
	}

	public void setProfesionales(Set<Profesional> profesionales) {
		this.profesionales = profesionales;
	}

	@Override
	public String toString() {
		return "Especialidad [id_especialidad=" + id_especialidad + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
  
}
