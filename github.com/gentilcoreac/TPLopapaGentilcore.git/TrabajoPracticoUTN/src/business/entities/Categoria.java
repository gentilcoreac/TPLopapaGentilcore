package business.entities;

import java.io.Serializable;

public class Categoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_categoria;
	private String descripcion;

	
	public int getId() {
		return id_categoria;
	}
	public void setId(int id) {
		this.id_categoria = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Categoria(){			
	}
	
	public Categoria(int id, String descripcion) {
		this.id_categoria = id;
		this.descripcion = descripcion;   
		}
	
	@Override
	public String toString() {
		return  id_categoria + "-" + descripcion;
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Categoria && ((Categoria)o).getId()==this.getId());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId()).hashCode();
	}
}