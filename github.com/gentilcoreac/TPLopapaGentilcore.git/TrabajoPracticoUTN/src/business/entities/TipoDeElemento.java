package business.entities;

import java.io.Serializable;
import java.util.Date;

public class TipoDeElemento implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id_tipodeelemento;
	private String nombre;
	private int cant_max_res_pen; 
	private int limite_horas_res; 
	private int dias_max_anticipacion;			
	private boolean only_encargados;
	
	public int getId() {
		return id_tipodeelemento;
	}
	public void setId(int id) {
		this.id_tipodeelemento = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCant_max_res_pen() {
		return cant_max_res_pen;
	}
	public void setCant_max_res_pen(int cant_max_res_pen) {
		this.cant_max_res_pen = cant_max_res_pen;
	}
	public int getLimite_horas_res() {
		return limite_horas_res;
	}
	public void setLimite_horas_res(int limite_horas_res) {
		this.limite_horas_res = limite_horas_res;
	}
	public int getDias_max_anticipacion() {
		return dias_max_anticipacion;
	}
	public void setDias_max_anticipacion(int dias_max_anticipacion) {
		this.dias_max_anticipacion = dias_max_anticipacion;
	}

	public boolean isOnly_encargados() {
		return only_encargados;
	}
	public void setOnly_encargados(boolean only_encargados) {
		this.only_encargados = only_encargados;
	}
	public TipoDeElemento(int id, String nombre, int cant_max_res_pen, int limite_horas_res,
			int dias_max_anticipacion,boolean solo_encargados) {
		this.id_tipodeelemento = id;
		this.nombre = nombre;
		this.cant_max_res_pen = cant_max_res_pen;
		this.limite_horas_res = limite_horas_res;
		this.dias_max_anticipacion = dias_max_anticipacion;
		this.only_encargados=solo_encargados;
	}

	public TipoDeElemento(){
	}
	
	
	
   @Override
   public boolean equals(Object o){
	   return (o instanceof TipoDeElemento && ((TipoDeElemento)o).getId()==this.getId());
   }
   
   @Override
   public int hashCode(){
	   return ((Integer)this.getId()).hashCode();
   }

	public String toString(){
		return id_tipodeelemento + "-" + nombre;}
}
		
	
	

