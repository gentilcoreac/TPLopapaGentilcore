package business.entities;

import java.io.Serializable;
import java.util.Date;



public class Reserva implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id_reserva;
	private Persona persona;
	private Elemento elemento;
	private Date fecha_hora_reserva_hecha;
	private Date fecha_hora_desde_solicitada;
	private Date fecha_hora_hasta_solicitada;   
	private Date fecha_hora_entregado;
	private String detalle;

	
	public Reserva(){
	}
		
	public Reserva(int id_reserva, Persona id_persona, Elemento id_elemento, Date fecha_hora_reserva_hecha,
			Date fecha_hora_desde_solicitada, Date fecha_hora_hasta_solicitada, Date fecha_hora_entregado,
			String detalle) {
		super();
		this.id_reserva = id_reserva;
		this.persona = id_persona;
		this.elemento = id_elemento;
		this.fecha_hora_reserva_hecha = fecha_hora_reserva_hecha;
		this.fecha_hora_desde_solicitada =fecha_hora_desde_solicitada;
		this.fecha_hora_hasta_solicitada = fecha_hora_hasta_solicitada;
		this.fecha_hora_entregado = fecha_hora_entregado;
		this.detalle = detalle;
	}

	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}


	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona id_persona) {
		this.persona = id_persona;
	}

	public Elemento getElemento() {
		return elemento;
	}
	public void setElemento(Elemento id_elemento) {
		this.elemento = id_elemento;
	}

	public Date getFecha_hora_reserva_hecha() {
		return fecha_hora_reserva_hecha;
	}
	public void setFecha_hora_reserva_hecha(Date fecha_hora_reserva_hecha) {
		this.fecha_hora_reserva_hecha = fecha_hora_reserva_hecha;
	}

	public Date getFecha_hora_desde_solicitada() {
		return fecha_hora_desde_solicitada;
	}
	public void setFecha_hora_desde_solicitada(Date fecha_hora_desde_solicitada) {
		this.fecha_hora_desde_solicitada = fecha_hora_desde_solicitada;
	}

	public Date getFecha_hora_hasta_solicitada() {
		return fecha_hora_hasta_solicitada;
	}
	public void setFecha_hora_hasta_solicitada(Date fecha_hora_hasta_solicitada) {
		this.fecha_hora_hasta_solicitada = fecha_hora_hasta_solicitada;
	}

	public Date getFecha_hora_entregado() {
		return fecha_hora_entregado;
	}
	public void setFecha_hora_entregado(Date fecha_hora_entregado) {
		this.fecha_hora_entregado = fecha_hora_entregado;
	}

	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
		
	@Override
	public String toString(){
		String fd=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getFecha_hora_desde_solicitada());
		return "\nReservaId:"+String.valueOf(getId_reserva())
		      +"\nIdElemento:"+String.valueOf(getElemento().getId_elemento())
		      +"\nNombre del Elemento:"+getElemento().getNombre()
		      +"\nReservado desde el:"+fd
		      +"\nhasta el:"+new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getFecha_hora_hasta_solicitada());
	}
}
