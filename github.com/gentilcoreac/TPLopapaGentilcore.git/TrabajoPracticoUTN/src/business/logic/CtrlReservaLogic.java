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
import tools.BookingException;
import tools.Campo;




public class CtrlReservaLogic {
	private DataReserva datRes;
	public ArrayList<Reserva> reservas; 

	
	public CtrlReservaLogic(){

		datRes = new DataReserva();
//		datPer = new DataPersona();
//		datElem = new DataElemento();
		reservas=new ArrayList<Reserva>();
	}
	
	public ArrayList<Reserva> getSome(Persona persona,Campo.TipoBusquedaR tipob,Reserva res)throws Exception{
		if(persona.getCategoria().getDescripcion().equals("Administrador")){
			return datRes.getSome(tipob, res);
		}
		else{
			return datRes.getSome(persona,tipob, res);
		}
	}
	
	
	
	public void add(Reserva r) throws SQLException, AppDataException{

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
	
	public Reserva getOne(int id)throws Exception{
		Reserva res=new Reserva();
		res.setId_reserva(id);
		return datRes.getOne(res);
	}
	
	public Reserva getOne(int idr,Persona p)throws Exception{
		if(p.getCategoria().getDescripcion().equals("Administrador")){
			return getOne(idr);
		}
		return datRes.getOne(idr, p);
	}
	
	public void retornaErroresFechas(Date fd,Date fh,Elemento ele)throws BookingException,Exception{
		Exception ex=new Exception("");
		if(!noEsFechaPasada(fd)){
			throw new BookingException(ex,"Fecha incorrecta: no puede reservar con una fecha-hora pasada");
		}
		if(!isFHastaMayorQFDesde(fd, fh)){
			throw new BookingException(ex, "La fecha-hora hasta debe ser posterior a la fecha-hora desde");
		}
		java.util.Date hoy=Calendar.getInstance().getTime();
		int diasMaxAnt=ele.getTipo().getDias_max_anticipacion();
		float diasEntre=getDaysBetween(fd,hoy);
		if(diasEntre >diasMaxAnt){
			Calendar calendario=Calendar.getInstance();
			calendario.setTime(hoy);
			calendario.add(Calendar.DATE,diasMaxAnt);
			throw new BookingException(ex, "No puede reservar este elemento luego del "
					+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendario.getTime()));
			}
		int horasMaxRes=ele.getTipo().getLimite_horas_res();
		float horasEntre=getHoursBetween(fh, fd);
		if(horasEntre>horasMaxRes){
			throw new BookingException(ex,"La reserva no puede durar mas de "+String.valueOf(horasMaxRes)+" horas");
		}
		if(getReservasEnIntervalo(ele.getId_elemento(), fd, fh)>0){
			throw new BookingException(ex, "No se puede reservar en ese intervalo,otra reserva interfiere."
					+ "Consulte las reservas del elemento");
		}
		
	}
	
	public void validaAlta(Reserva res)throws BookingException,Exception{
		Persona persona=res.getPersona();
		Elemento elemento=res.getElemento();
		Exception ex=new Exception("");
		if(null==elemento){
			throw new BookingException(ex,"El elemento no existe");
		}
		if(null==persona){
			throw new BookingException(ex,"La Persona no existe");
		}
		retornaErroresFechas(res.getFecha_hora_desde_solicitada(),res.getFecha_hora_hasta_solicitada(),elemento);
		if(!sePuedeCrear(persona, res)){
			throw new BookingException(ex,"Solo los encargados pueden reservar este tipo de elemento");
		}
		if(hayLimtResPen(persona, res)){
			throw new BookingException(ex,"No se pueden reservar mas elementos de este tipo\n"
                    + "Limite de reservas pendientes alcanzadas para el tipo:"
                    + res.getElemento().getTipo().getNombre());
		}
	}
}

