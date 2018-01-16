package tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Emailer {

	private static Emailer instance;
	private Properties props;
	
	public static Emailer getInstance()throws Exception{
		if(instance==null){
			instance=new Emailer();
		}
		return instance;
	}
	
	
    private Emailer() throws Exception{
		
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream("app.properties");
		try {
			props = new Properties();
			props.load(inputStream);
			/*
			 * props.put("mail.smtp.auth", "true");
			 * props.put("mail.smtp.starttls.enable", "true");
			 * props.put("mail.smtp.host", "smtp.gmail.com");
			 * props.put("mail.smtp.port", "587");
			 * props.put("mail.username", "somemail@gmail.com");
			 * props.put("mail.password","someRandomwPassword");
			 */
			//esto lo sacamos del archivo app.properties y es mas facil ademas si un dia queremos cambiarlo
			//tocamos el archivo nomas
		} catch (IOException e) {
			throw e;
		}
		
	}
    
    /*funcion para mandar mail*/
    public void send(String to, String subject, String body) throws Exception{

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication(username, password);
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });//arma la sesion 

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			
			//despues viene el recipients que setea quien lo va a recibir,recibe dos cosas,que tipo de recipien es y un arraylist
			//de direcciones de internet o una sola,en el InternetAddress.parse(to) los convierte en un arraylist de internetaddress
			//despues le digo que tipo de recipient es, en este caso To (o sra drstinatario)
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to)); //"adrianmeca@gmail.com"
			message.setSubject(subject); //"Testing Subject"
			//subject es el tema,el asunto de arriba
			message.setText(body); //"Dear Mail Crawler,\n\n No spam to my email, please!"

			Transport.send(message);

		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			throw e;
		}
		
	}
}
