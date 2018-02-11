package data;
import java.sql.*;

import org.apache.logging.log4j.Level;

import tools.AppDataException;

public class FactoryConexion {

//	private String host="localhost";
//	private String port="3306";
//	private String user="root";			
//	private String password="root";		
//	private String db="BaseReservas";
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10220459";
	private String user="sql10220459";			
	private String password="yfzXjplGi2";
	
	private static FactoryConexion instancia;
	
	public FactoryConexion()throws AppDataException{
		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new AppDataException(e,"error al registrar el driver de conexion para la base de datos",Level.ERROR);
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
//				conn = DriverManager.getConnection(
//						"jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
//				String URL = "jdbc:mysql://mysql{node_id}-{environment_name}.{hoster_domain}/{dbname}";
				conn = DriverManager.getConnection(url,user,password);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al conectarse a la base de datos.\n"+sqlex.getCause(),Level.ERROR);
		}
		cantConn++;
		return conn;
	}

	public void releaseConn()throws SQLException,AppDataException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al cerrar conexion",Level.ERROR);
		}
	}
}