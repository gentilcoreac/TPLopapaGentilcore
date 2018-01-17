package data;
import java.sql.*;

import tools.AppDataException;

public class FactoryConexion {
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";			////////COMPLETAR///////
	private String password="1618314";		////////COMPLETAR///////
	private String db="BaseReservas";			////////COMPLETAR///////
	
	private static FactoryConexion instancia;
	
	public FactoryConexion()throws AppDataException{
		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new AppDataException(e,"error al registrar el driver de conexion para la base de datos");
		}
	}
	
	public static FactoryConexion getInstancia()throws SQLException,AppDataException{
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