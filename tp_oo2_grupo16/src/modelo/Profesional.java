package modelo;

import java.util.Set;
import java.util.HashSet;

public class Profesional extends Persona {
	
    private String matricula;
    private Set<Especialidad> especialidades = new HashSet<>();

    public Profesional() {}

	public Profesional(String nombre, String apellido, String dni, String matricula) {
		super(nombre, apellido, dni);
		this.matricula = matricula;
		
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	@Override
	public String toString() {
		return "Profesional"+ super.toString() +", matricula=" + matricula;
	}

    
}
