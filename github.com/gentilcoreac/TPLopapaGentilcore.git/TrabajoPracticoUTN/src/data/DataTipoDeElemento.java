package data;

import java.sql.*;
import java.util.ArrayList;


import business.entities.TipoDeElemento;
import tools.AppDataException;

public class DataTipoDeElemento {
	
	//id_tipodeelemento,  nombre,  cantmaxrespen,  limite_horas_res,  dias_max_anticipacion

	public TipoDeElemento getOne(TipoDeElemento tde_p)throws SQLException,AppDataException{
		TipoDeElemento te = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_tipodeelemento, nombre, cantmaxrespen, limite_horas_res, dias_max_anticipacion "
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
				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al recuperar un tipo de elemento");
		}
		
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex,"Error al cerrar conexion,PreparedStatement o ResultSet");
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
					"select id_tipodeelemento,nombre,cantmaxrespen,limite_horas_res,dias_max_anticipacion "
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
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al recuperar un tipo de elemento");
		}
		
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException  sqlex) {
				throw new AppDataException(sqlex,"Error al cerrar la conexion,PreparedStatement o Resultset");
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
					tiposelementos.add(te);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al recuperar todos los tipos de elementos");
		}
		finally{
			try{
				if(stmt!=null){stmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,Statement o ResultSet");
			}
		}
		return tiposelementos;
	}
	
	
	public void add(TipoDeElemento tde)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet keyRes=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "insert into tipodeelemento(nombre,cantmaxrespen,limite_horas_res,dias_max_anticipacion) values(?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, tde.getNombre());
			pstmt.setInt(2, tde.getCant_max_res_pen());
			pstmt.setInt(3, tde.getLimite_horas_res());
			pstmt.setInt(4, tde.getDias_max_anticipacion());
			pstmt.executeUpdate();
			keyRes=pstmt.getGeneratedKeys();
			if(keyRes!=null && keyRes.next()){
				tde.setId(keyRes.getInt(1));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al agregar tipo de elemento");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(keyRes!=null){keyRes.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion,PreparedStatement o Resultset");
			}
		}
	}
	
	public void update(TipoDeElemento te)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement("update tipodeelemento set nombre=?, set cantmaxrespen=?, set limite_horas_res=?, set dias_max_anticipacion=?  where id_tipodeelemento=?");
			pstmt.setString(1,te.getNombre() );
			pstmt.setInt(2,te.getCant_max_res_pen() );
			pstmt.setInt(3, te.getLimite_horas_res());
			pstmt.setInt(4, te.getDias_max_anticipacion());
			pstmt.setInt(5,te.getId());
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar tipo de elemento");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement");
			}
		}
	}
	
	public void delete(TipoDeElemento te)throws SQLException,AppDataException{
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try{
			pstmt1=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from cat_tip where id_tipodeelemento=?;");
			pstmt1.setInt(1, te.getId());
			pstmt1.executeUpdate();
			pstmt2=FactoryConexion.getInstancia().getConn().prepareStatement("delete from tipodeelemento where id_tipodeelemento=?;");
			pstmt2.setInt(1, te.getId());
			pstmt2.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al borrar tipo de elemento");
		}
		finally{
			try{
				if(pstmt1!=null){pstmt1.close();}
				if(pstmt2!=null){pstmt2.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion o PreparedStatement");
			}
		}
	}
}
