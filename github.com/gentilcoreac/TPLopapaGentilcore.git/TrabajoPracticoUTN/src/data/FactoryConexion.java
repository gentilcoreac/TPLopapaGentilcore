package data;
import java.sql.*;

import javax.swing.JOptionPane;

import tools.AppDataException;

public class FactoryConexion {
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";			////////COMPLETAR///////
	private String password="root";		////////COMPLETAR///////
	private String db="BaseReservas";			////////COMPLETAR///////
	
	private static FactoryConexion instancia;
	
	public FactoryConexion(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static FactoryConexion getInstancia()throws SQLException{
		if(FactoryConexion.instancia == null){
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
	}
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn()throws SQLException,AppDataException{
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(
						"jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al conectarse a la base de datos");
		}
		cantConn++;
		return conn;
	}
/////////////
	public void releaseConn()throws SQLException,AppDataException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al cerrar conexion");
		}
	}
}