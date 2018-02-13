package data;

import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import business.entities.TipoDeElemento;
import tools.AppDataException;

public class DataTipoDeElemento {
	
	

	public TipoDeElemento getOne(TipoDeElemento tde_p)throws SQLException,AppDataException{
		TipoDeElemento te = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * "
					+ " from tipodeelemento "
					+ " where id_tipodeelemento=? "); 	
			stmt.setInt(1,tde_p.getId());
			rs= stmt.executeQuery();
			if(rs != null && rs.next()){
				te = new TipoDeElemento();
				te.setId(rs.getInt("id_tipodeelemento"));
				te.setNombre(rs.getString("nombre"));
				te.setCant_max_res_pen(rs.getInt("cantmaxrespen"));
				te.setLimite_horas_res(rs.getInt("limite_horas_res"));
				te.setDias_max_anticipacion(rs.getInt("dias_max_anticipacion"));
				te.setOnly_encargados(rs.getBoolean("only_encargados"));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al recuperar un tipo de elemento",Level.ERROR);
		}
		
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex,"Error al cerrar conexion,PreparedStatement o ResultSet",Level.ERROR);
			}
		}
		return te;
	}
	
	
	

	public TipoDeElemento getOne(int tde_p)throws SQLException,AppDataException{
		TipoDeElemento te = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select* "
					+ " from tipodeelemento "
					+ " where id_tipodeelemento=? ");
			stmt.setInt(1,tde_p);
			rs= stmt.executeQuery();
			if(rs != null && rs.next()){
				te = new TipoDeElemento();
				te.setId(rs.getInt("id_tipodeelemento"));
				te.setNombre(rs.getString("nombre"));
				te.setCant_max_res_pen(rs.getInt("cantmaxrespen"));
				te.setLimite_horas_res(rs.getInt("limite_horas_res"));
				te.setDias_max_anticipacion(rs.getInt("dias_max_anticipacion"));	
				te.setOnly_encargados(rs.getBoolean("only_encargados"));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al recuperar un tipo de elemento",Level.ERROR);
		}
		
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException  sqlex) {
				throw new AppDataException(sqlex,"Error al cerrar la conexion,PreparedStatement o Resultset",Level.ERROR);
			}
		}
		return te;
	}
	
	public TipoDeElemento getByName(TipoDeElemento tde)throws Exception{
		TipoDeElemento te=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement("select* from tipodeelemento "
																		  + "where nombre=?;");
			pstmt.setString(1, tde.getNombre());
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				te = new TipoDeElemento();
				te.setId(rs.getInt("id_tipodeelemento"));
				te.setNombre(rs.getString("nombre"));
				te.setCant_max_res_pen(rs.getInt("cantmaxrespen"));
				te.setLimite_horas_res(rs.getInt("limite_horas_res"));
				te.setDias_max_anticipacion(rs.getInt("dias_max_anticipacion"));	
				te.setOnly_encargados(rs.getBoolean("only_encargados"));
			}
					
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al recuperar un tipo de elemento por su nombre",Level.ERROR);
		}
		finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar PreparedStatement, Resultset o conexion",Level.ERROR);
			}
		}
		return te;
		
	}
	
	public ArrayList<TipoDeElemento> getAll()throws SQLException,AppDataException{
		ArrayList<TipoDeElemento> tiposelementos=new ArrayList<TipoDeElemento>();
		Statement stmt=null;
		ResultSet res=null;
		try{
			stmt= FactoryConexion.getInstancia().getConn().createStatement();
			res=stmt.executeQuery("select* from tipodeelemento; ");
			if(res!=null){
				while(res.next()){
					TipoDeElemento te=new TipoDeElemento();
					te.setId(res.getInt("id_tipodeelemento"));
					te.setNombre(res.getString("nombre"));
					te.setCant_max_res_pen(res.getInt("cantmaxrespen"));
					te.setLimite_horas_res(res.getInt("limite_horas_res"));
					te.setDias_max_anticipacion(res.getInt("dias_max_anticipacion"));
					te.setOnly_encargados(res.getBoolean("only_encargados"));
					tiposelementos.add(te);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al recuperar todos los tipos de elementos",Level.ERROR);
		}
		finally{
			try{
				if(stmt!=null){stmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,Statement o ResultSet",Level.ERROR);
			}
		}
		return tiposelementos;
	}
	
	
	public void add(TipoDeElemento tde)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet keyRes=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "insert into tipodeelemento(nombre,cantmaxrespen,"
					+ "limite_horas_res,dias_max_anticipacion,only_encargados) "
					+ "values(?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, tde.getNombre());
			pstmt.setInt(2, tde.getCant_max_res_pen());
			pstmt.setInt(3, tde.getLimite_horas_res());
			pstmt.setInt(4, tde.getDias_max_anticipacion());
			pstmt.setBoolean(5, tde.isOnly_encargados());
			pstmt.executeUpdate();
			keyRes=pstmt.getGeneratedKeys();
			if(keyRes!=null && keyRes.next()){
				tde.setId(keyRes.getInt(1));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al agregar tipo de elemento.\n"
					+ "Verifique que el nombre sea unico.",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(keyRes!=null){keyRes.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion,PreparedStatement o Resultset",Level.ERROR);
			}
		}
	}
	
	public void update(TipoDeElemento te)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "update tipodeelemento set "
					+ " cantmaxrespen=?,  limite_horas_res=?,"
					+ " dias_max_anticipacion=?, only_encargados=? "
					+ " where nombre=?");
			
			pstmt.setInt(1,te.getCant_max_res_pen() );
			pstmt.setInt(2, te.getLimite_horas_res());
			pstmt.setInt(3, te.getDias_max_anticipacion());
			pstmt.setBoolean(4, te.isOnly_encargados());
			pstmt.setString(5,te.getNombre());
			int rowsAffected=pstmt.executeUpdate();
			if(rowsAffected==0){
				throw new AppDataException(new Exception("Tipo de elemento inexistente\nno se pudo actualizar"),"Error");
				}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar tipo de elemento",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement",Level.ERROR);
			}
		}
	}
	
	public void delete(TipoDeElemento te)throws SQLException,AppDataException{
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		try{
			pstmt1=FactoryConexion.getInstancia().getConn().prepareStatement(""
																			+ " delete from reserva "
																			+ " where id_elemento in ( "
																			+ " select e.id_elemento "
																			+ " from elemento e "
																			+ " where e.id_tipodeelemento=? "
																			+ " );");
			pstmt1.setInt(1, te.getId());
			pstmt1.executeUpdate();
			
			pstmt2=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from elemento where id_tipodeelemento=?;");
			pstmt2.setInt(1, te.getId());
			pstmt2.executeUpdate();
			
			pstmt3=FactoryConexion.getInstancia().getConn().prepareStatement("delete from tipodeelemento where nombre=?;");
			pstmt3.setString(1, te.getNombre());
			int rowsAffected=pstmt3.executeUpdate();
			if(rowsAffected==0){
				throw new AppDataException(new Exception("Tipo de elemento inexistente\nNo se pudo eliminar"),"Error");
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al borrar tipo de elemento",Level.ERROR);
		}
		finally{
			try{
				if(pstmt1!=null){pstmt3.close();}
				if(pstmt2!=null){pstmt3.close();}
				if(pstmt3!=null){pstmt3.close();}
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion o PreparedStatement",Level.ERROR);
			}
		}
	}
}
