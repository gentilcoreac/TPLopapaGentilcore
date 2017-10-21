package tools;

import java.util.Date;

public class DateFormateada extends Date{

	private Date fecha;
	
	public Date getFecha(){
		return fecha;
	}
	public void setFecha(Date date){
		fecha=date;
	}
	public DateFormateada(Date date){
		this.setFecha(date);
	}
	
	public String toString(){
		return this.getFecha()==null?null:new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.getFecha());
	}
}
