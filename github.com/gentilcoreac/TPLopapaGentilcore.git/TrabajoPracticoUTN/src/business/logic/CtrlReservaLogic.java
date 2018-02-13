package business.logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.entities.TipoDeElemento;
import data.DataReserva;
import tools.AppDataException;
import tools.BookingException;
import tools.Campo;




public class CtrlReservaLogic {
	private DataReserva datRes;
	public ArrayList<Reserva> reservas; 

	
	public CtrlReservaLogic(){

		datRes = new DataReserva();
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
	
	public Boolean hasItStarted(Reserva res)throws Exception{
		return !isReservaPendiente(res);
	}
	
	public Boolean perPuedeEliminar(Persona persona,Reserva res)throws Exception{
		if(!isReservaPendiente(res) && !persona.getCategoria().getDescripcion().equals("Administrador")){
			return false;
		}
		else{
			return true;
		}
	}
	
	//sin problema de categoria
	public Boolean sinProblemDeCat(Persona persona,Reserva res){
		if(res.getElemento().getTipo().isOnly_encargados() && !persona.getCategoria().getDescripcion().equals("Encargado")){
			return false;
		}
		else{
			return true;
		}
	}
	public int getNumResPen(Persona persona,Reserva reserva)throws Exception{
		return datRes.getNumResPen(persona, reserva);
	}
	public int getNumResPen(Persona persona,TipoDeElemento te)throws Exception{
		Reserva res=new Reserva();
		Elemento ele=new Elemento();
		ele.setTipo(te);
		res.setElemento(ele);
		return datRes.getNumResPen(persona, res);
	}
	public Boolean hayLimtResPen(Persona persona,Reserva res)throws Exception{
		return getNumResPen(persona, res)>=res.getElemento().getTipo().getCant_max_res_pen()?true:false;
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
		if(!sinProblemDeCat(persona, res)){
			throw new BookingException(ex,"Solo los encargados pueden reservar este tipo de elemento");
		}
		if(hayLimtResPen(persona, res)){
			throw new BookingException(ex,"No se pueden reservar mas elementos de este tipo\n"
                    + "Limite de reservas pendientes alcanzadas para el tipo:"
                    + res.getElemento().getTipo().getNombre());
		}
	}
	
	public void validaCierre(Reserva res)throws BookingException,Exception{
		Exception ex=new Exception("");
		if(res==null){
			throw new BookingException(ex,"Reserva inexistente");
		}
		if(!isFCierreMayorQFDesde(res.getFecha_hora_entregado(),res.getFecha_hora_desde_solicitada())){
			throw new BookingException(ex,"La fecha de cierre debe ser posterior al inicio de la reserva");
		}
		if(!hasItStarted(res)){
			throw new BookingException(ex,"No se puede cerrar la reserva:aun no ha iniciado.");
		}
	}
	
	public void validaBaja(Reserva res,Persona per)throws BookingException,Exception{
		Exception ex=new Exception("");
		if(res==null){
			throw new BookingException(ex,"Reserva inexistente");
		}
		if(!perPuedeEliminar(per, res)){
			throw new BookingException(ex,"Solo se pueden eliminar reservas pendientes");
		}
	}
}

