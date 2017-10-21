/*package tools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ui.Desktop.ListadoElementos;



public class BotonLabel extends JLabel{

	public BotonLabel(String rutaIcon,String rutaIconFocus,String rutaIconPressed){
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				setIcon(new ImageIcon(ListadoElementos.class.getResource(rutaIconFocus)));}

		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setIcon(new ImageIcon(ListadoElementos.class.getResource(rutaIcon)));
			}
		});
		
		
//		lblTipo.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//			}
//		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setIcon(new ImageIcon(ListadoElementos.class.getResource(rutaIconPressed)));
				
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setIcon(new ImageIcon(ListadoElementos.class.getResource(rutaIcon)));
			}
		});
		
		setIcon(new ImageIcon(ListadoElementos.class.getResource(rutaIcon)));
		//this.setText("que onda");
		
	}
	
	public BotonLabel(){
		
	}
}
*/