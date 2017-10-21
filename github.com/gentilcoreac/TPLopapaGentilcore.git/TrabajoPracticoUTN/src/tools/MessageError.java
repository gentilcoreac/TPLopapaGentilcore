package tools;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MessageError {

	//showMessageDialog(frmLogin, "Tu vieja", "cartelito",JOptionPane.NO_OPTION,new ImageIcon(getClass().getResource("perrito.png"))
	
	public static void showMessageDialog(Component parentComponent,
							             Object message)throws HeadlessException{
		
		JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.NO_OPTION, new ImageIcon(MessageError.class.getResource("perrito.png")));
	}
		
	
}
