package business.logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;


import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.entities.TipoDeElemento;
import data.DataElemento;
import data.DataPersona;
import data.DataReserva;
import data.DataTipoDeElemento;
import tools.AppDataException;
import tools.Campo;




public class CtrlReservaLogic {
	private DataReserva datRes;
	private DataPersona datPer;
	private DataElemento datElem;
	public ArrayList<Reserva> reservas; 

	
	public CtrlReservaLogic(){

		datRes = new DataReserva();
//		datPer = new DataPersona();
//		datElem = new DataElemento();
		reservas=new ArrayList<Reserva>();
	}
	
	public ArrayList<Reserva> getAll()throws Exception{					////////////////////////////////////////////////////////////////////////////////////////////////////
	//	if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getAll();
	//	}
	//	else{
	//		return datRes.getSome(persona, res);
	//	}
	}
	
	public ArrayList<Reserva> getSome(Persona persona, Reserva res,int indice,int cantidad)throws Exception{
		if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getSome(res);
		}
		else{
			return datRes.getSome(persona, res);
		}
	}

	public ArrayList<Reserva> getAll(Persona persona, Persona p_buscada)throws Exception{	////////////////////////////////////////////////////////////////////////

		if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getAll();
		}
		else{
			return datRes.getAll(p_buscada);
		}
	}

	/*
	public ArrayList<Reserva> getSome(Persona persona,Campo.TipoBusquedaR tipob,Reserva res,int indice,int cantidad)throws Exception{
		if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getSome(tipob, res, indice, cantidad);
		}
		else{
			return datRes.getSome(persona,tipob, res, indice, cantidad);
		}
	}
	
	public int getCantidad(Persona persona,Campo.TipoBusquedaR tipob,Reserva reserva)throws Exception{
		
		if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getCantidad(tipob, reserva);
		}else{
			return datRes.getCantidad(persona,tipob, reserva);
		}
		
	}*/
	
	public void add(Reserva r) throws SQLException, AppDataException{
		/*Reserva res = new Reserva();
		try {
			res=datRes.getOne(r);
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		datRes.add(r);
	}

	public Boolean isFHastaMayorQFDesde(java.util.Date fechaHoraD,java.util.Date fechaHoraH)throws Exception,ParseException{
		

		return fechaHoraD.compareTo(fechaHoraH)<0?true:false;
	}
	
	public Boolean isFCierreMayorQFDesde(java.util.Date fechaHoraC,java.util.Date fechaHoraD)throws Exception,ParseException{
		return fechaHoraC.compareTo(fechaHoraD)<0?false:true;
	}
	
	public Boolean isReservaPendiente(Reserva res)throws Exception{
		return res.getFecha_hora_desde_solicitada().compareTo(Calendar.getInstance().getTime())<0?false:true;
	}
	
	public Boolean sePuedeCerrar(Reserva res)throws Exception{
		return !isReservaPendiente(res);
	}
	
	public Boolean sePuedeEliminar(Persona persona,Reserva res)throws Exception{
		if(!isReservaPendiente(res) && !persona.getCategoria().getDescripcion().equals("Administrador")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Boolean sePuedeCrear(Persona persona,Reserva res){
		if(res.getElemento().getTipo().isOnly_encargados() && !persona.getCategoria().getDescripcion().equals("Encargado")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Boolean hayLimtResPen(Persona persona,Reserva res)throws Exception{
		return datRes.numResPen(persona, res)>=res.getElemento().getTipo().getCant_max_res_pen()?true:false;
	}
	
	public float getDaysBetween(Date fecha1,Date fecha2){
		long diff=fecha1.getTime()-fecha2.getTime();//te da la diferencia en milisegundos
		float dias = (float)diff / (1000*60*60*24);
		return dias;
	}
	
	public float getHoursBetween(Date fecha1,Date fecha2){
		long diff=fecha1.getTime()-fecha2.getTime();
		float horas=(float)diff/(1000*60*60);
		return horas;
	}
	
	public Boolean noEsFechaPasada(Date fecha){
		return fecha.compareTo(Calendar.getInstance().getTime())<0?false:true;
	}
	
	public int getReservasEnIntervalo(int idEle,Date fechaD,Date fechaH)throws Exception{
		return datRes.getReservasEnIntervalo(idEle, fechaD, fechaH);
	}
	
	public int getMaxId()throws Exception{
		return datRes.getMaxId();
	}
	

	
	/*
	public void update(Reserva r) throws SQLException, AppDataException{
		datRes.update(r);
	}*/
	
	public void updateParaCerrarRes(Reserva r) throws SQLException, AppDataException{
		datRes.updateParaCerrarRes(r);
	}

	public void delete(Reserva r) throws SQLException, AppDataException{
		datRes.delete(r);
	}
	
	public Reserva getOne(Reserva r)throws Exception{
		return datRes.getOne(r);
	}	


	
}

