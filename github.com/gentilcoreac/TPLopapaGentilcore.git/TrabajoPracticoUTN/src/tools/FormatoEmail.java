package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatoEmail {

	
	/**
     * Valida si es correcta la dirección de correo electrónica dada.
     *@param email
     *@return true si es correcta o false si no lo es.
     */
    public static boolean esEmailCorrecto(String email) {
        boolean valido = false;
        
        Pattern patronEmail = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");    
        Matcher mEmail = patronEmail.matcher(email.toLowerCase());
        if (mEmail.matches()){
           valido = true;  
        }
        return valido;
    }
	
	
}
