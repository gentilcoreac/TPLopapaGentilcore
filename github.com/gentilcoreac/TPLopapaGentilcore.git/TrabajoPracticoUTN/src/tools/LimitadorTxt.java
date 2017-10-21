package tools;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LimitadorTxt {

	
	public static enum Campo{ELENOM(45),CATDESC(100),PERDNI(10),PERNOMBRE(45),PERAPELLIDO(45),
		                     PERUSUARIO(20),PERPASS(20),PEREMAIL(45),RESDETALLE(140),TIPNOMBRE(45);
		                     private final int maxCaracteres;
		                     private Campo(final int maxC){this.maxCaracteres=maxC;}
		                     @Override public String toString(){return String.valueOf(this.maxCaracteres);}}
	
	public static void MaxCaracteres(LimitadorTxt.Campo max,JTextField txt){
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(txt.getText().length()>=max.maxCaracteres){
					evt.consume();
				}
			}
		});
	}
	public static void MaxCaracteres(LimitadorTxt.Campo max,JPasswordField pwf){
		pwf.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent evt) {
			if(pwf.getPassword().length>=max.maxCaracteres){
				evt.consume();
			}
		}
	});
	}
	
	public static void MaxCaracteres(LimitadorTxt.Campo max,JTextArea jtxta){
		jtxta.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent evt) {
			if(jtxta.getText().length()>=max.maxCaracteres){
				evt.consume();
			}
		}
	});
	}
}
