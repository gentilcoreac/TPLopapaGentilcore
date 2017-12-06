package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Campo {

	public static String Mensaje;
	public static String getMensaje() {
		return Mensaje;
	}

	public static void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

	public enum tipo{EMAIL,DNI,INDICE,ID,FECHA,HORA,MAXRESPEN,DIASANT,LIMHOR,OTRO}
	
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
		if(campo.isEmpty() || campo==null){
			//JOptionPane.showMessageDialog(null,"Complete todos los campos por favor", "", JOptionPane.INFORMATION_MESSAGE);
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
		case MAXRESPEN:return validaMaxResPen(campo);
		case DIASANT:return validaDiasAnt(campo);
		case LIMHOR:return validaLimHor(campo);
		default:break;
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
			//JOptionPane.showMessageDialog(null,"hora invalida\n"+hora, "",JOptionPane.INFORMATION_MESSAGE);
			Mensaje="hora invalida\n"+hora;
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
			//JOptionPane.showMessageDialog(null,"fecha invalida\n"+fecha, "",JOptionPane.INFORMATION_MESSAGE);
			Mensaje="fecha invalida\n"+fecha;
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
        	//JOptionPane.showMessageDialog(null, "Email invalido","",JOptionPane.INFORMATION_MESSAGE);
        	Mensaje="Email invalido";
        }
        return valido;
	}
	
	private static boolean validaDni(String dni){
		boolean correcto=dni.matches("[0-9]+");
		if(!correcto){
			//JOptionPane.showMessageDialog(null, "Dni invalido","",JOptionPane.INFORMATION_MESSAGE);
			Mensaje="Dni invalido";
		}
		return correcto;
	}
	
	private static boolean validaIndice(String indice){
		boolean correcto=indice.matches("[1-9][0-9]*");
		if(!correcto){
			//JOptionPane.showMessageDialog(null, "El indice debe tener un valor numerico mayor a 0","",JOptionPane.INFORMATION_MESSAGE);
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
			//JOptionPane.showMessageDialog(null, "El id debe tener un valor numerico mayor a 0","",JOptionPane.INFORMATION_MESSAGE);
			Mensaje="El id debe tener un valor numerico mayor a 0";
		}
		return correcto;
	}
}
