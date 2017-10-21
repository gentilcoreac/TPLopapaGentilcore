package business.entities;

import java.io.Serializable;

public class Elemento implements Serializable{
	private int id_elemento;
	private String nombre; //este es opcional
	private TipoDeElemento tipo;
	
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId_elemento() {
		return id_elemento;
	}
	public void setId_elemento(int id_elemento) {
		this.id_elemento = id_elemento;
	}
	public TipoDeElemento getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeElemento tipo) {
		this.tipo = tipo;
	}	
	
	public Elemento(int id, String nombre, int id_elemento) {
		this.id_elemento = id;
		this.nombre = nombre;
		this.id_elemento = id_elemento;
	}
	
	public Elemento(){}	
	
}