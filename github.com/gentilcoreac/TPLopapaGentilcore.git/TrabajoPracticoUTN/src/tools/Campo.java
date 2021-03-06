package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Campo {

	public static String Mensaje;
	public static String getMensaje() {
		return Mensaje;
	}

	public static void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

	public enum tipo{EMAIL,DNI,INDICE,ID,FECHA,HORA,MAXRESPEN,DIASANT,LIMHOR,FECHAHORA,OTRO}
	
    public static enum TipoBusquedaR{ POR_IDRESERVA("Por Id de la Reserva"),POR_IDELEMENTO("Por Id del elemento"),
		POR_IDPERSONA("Por Id de la Persona"),PENDIENTES("Pendientes"),VENCIDAS("Vencidas"),TRAER_TODAS("Traer Todas");
    	private final String texto;
    	private TipoBusquedaR(final String texto){this.texto=texto;}
    	@Override
    	public String toString(){return texto;}}
    	
    public static enum TipoBusquedaE{ POR_ID("Por Id"),POR_NOMBRE("Por Nombre"),
	     POR_TIPO("Por Tipo"),POR_NOMBRE_Y_TIPO("Por Nombre y Tipo"),
	     POR_TIPO_Y_FH("Por Tipo y FH Disponible"),TRAER_TODOS("Traer Todos");
			private final String texto;
			private TipoBusquedaE(final String texto){this.texto=texto;}
			@Override
	    	public String toString(){return texto;}}
	
	public static boolean Valida(String campo,tipo tipoCampo){
		if(campo==null || campo.isEmpty()  ){
			Mensaje="Complete todos los campos requeridos por favor";
			return false;
			}
		
		switch(tipoCampo){
		case EMAIL:return validaEmail(campo);
		case DNI:return validaDni(campo);
		case INDICE: return validaIndice(campo);
		case ID:return validaId(campo);
		case FECHA:return validaFecha(campo);
		case HORA:return validaHora(campo);
		case FECHAHORA:return validaFechaHora(campo);
		case MAXRESPEN:return validaMaxResPen(campo);
		case DIASANT:return validaDiasAnt(campo);
		case LIMHOR:return validaLimHor(campo);
		default:break;
		}
		return true;
	}
	
	public static Date parseaFecha(String fecha) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		formatter.setLenient(false);
		return formatter.parse(fecha);
	}
	
	private static boolean validaFechaHora(String fechahora){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try{
		formatter.setLenient(false);
		formatter.parse(fechahora);}
		catch(ParseException pe){
			Mensaje="Fecha-Hora invalida";
			return false;
		}
		return true;
	}
	
	private static boolean validaHora(String hora){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try{
		formatter.setLenient(false);
		formatter.parse(hora);
		}
		catch(ParseException pe){
			Mensaje="Hora invalida\n"+hora;
			return false;
		}
		return true;
	}
	private static boolean validaFecha(String fecha){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
		formatter.setLenient(false);
		formatter.parse(fecha);
		}
		catch(ParseException pe){
			Mensaje="Fecha invalida\n"+fecha;
			return false;
		}
		return true;
	}
	private static boolean validaEmail(String email){
		
        boolean valido = false;
        
        Pattern patronEmail = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");    
        Matcher mEmail = patronEmail.matcher(email.toLowerCase());
        
        if (mEmail.matches()){
           valido = true;  
        }
        else{
        	Mensaje="Email invalido";
        }
        return valido;
	}
	
	private static boolean validaDni(String dni){
		boolean correcto=dni.matches("[0-9]+");
		if(!correcto){
			Mensaje="Dni invalido";
		}
		return correcto;
	}
	
	private static boolean validaIndice(String indice){
		boolean correcto=indice.matches("[1-9][0-9]*");
		if(!correcto){
			Mensaje="El indice debe tener un valor numerico mayor a 0";
		}
	    return correcto;
	}
	
	private static boolean validaMaxResPen(String maxResPen){
		boolean correcto=maxResPen.matches("[1-9][0-9]*");
		if(!correcto){
			Mensaje="La cantidad maxima de reservas pendientes debe\nser un numero mayor a 0";
		}
		return correcto;
	}
	
	private static boolean validaDiasAnt(String diasAnt){
		boolean correcto=diasAnt.matches("[1-9][0-9]*");
		if(!correcto){
			Mensaje="La cantidad maxima de dias de anticipacion debe\nser un numero mayor a 0";
		}
		return correcto;
	}
	
	private static boolean validaLimHor(String limHor){
		boolean correcto=limHor.matches("[1-9][0-9]*");
		if(!correcto){
			Mensaje="El limite maximo de tiempo de reserva debe\nser un numero mayor a 0";
		}
		return correcto;
	}
	
	private static boolean validaId(String id){
		boolean correcto=id.matches("[1-9][0-9]*");
		if(!correcto){
			Mensaje="El id debe tener un valor numerico mayor a 0";
		}
		return correcto;
	}
}
