package modelo;

import java.util.Date;

public class Turno {
    private int id_turno;
    private Profesional profesional;
    private Cliente cliente;
    private Servicio servicio;
    private Date fecha_hora;
    private String observaciones;
    private String estado;
    

    public Turno() {}


	public Turno(Profesional profesional, Cliente cliente, Servicio servicio, Date fecha_hora, String observaciones,
			String estado) {
		super();
		this.profesional = profesional;
		this.cliente = cliente;
		this.servicio = servicio;
		this.fecha_hora = fecha_hora;
		this.observaciones = observaciones;
		this.estado = estado;
	}


	public int getId_turno() {
		return id_turno;
	}


	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}


	public Profesional getProfesional() {
		return profesional;
	}


	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	public Date getFecha_hora() {
		return fecha_hora;
	}


	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Turno [id_turno=" + id_turno + ", profesional=" + profesional + ", cliente=" + cliente + ", servicio="
				+ servicio + ", fecha_hora=" + fecha_hora + ", observaciones=" + observaciones + ", estado=" + estado
				+ "]";
	}
    
    
    
    
}