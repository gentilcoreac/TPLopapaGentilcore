package data;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import java.sql.*;

import business.entities.*;
import tools.*;

public class DataCategoria {

	public ArrayList<Categoria> getAll()throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> categorias=new ArrayList<Categoria>();
		try{
			stmt=FactoryConexion.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("select* from categoria");
			if(rs!=null){
				while(rs.next()){
					Categoria cat=new Categoria();
					cat.setId(rs.getInt("id_categoria"));
					cat.setDescripcion(rs.getString("descripcion"));
					categorias.add(cat);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer todas las categorias de la base de datos",Level.ERROR);
			}		
		finally{
				try{
					if(rs!=null)rs.close();
					if(stmt!=null)stmt.close();
					FactoryConexion.getInstancia().releaseConn();
					}
				catch(SQLException sqlex){
					throw new AppDataException(sqlex,"Error al cerrar conexion, resultset o statement",Level.ERROR);
					}
		}	
		return categorias;
	}
	
	
	public Categoria getOne(Categoria cat)throws SQLException,AppDataException{
		Categoria c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from categoria where id_categoria=?");
			pstmt.setInt(1,cat.getId());
			rs= pstmt.executeQuery();
			if(rs != null && rs.next()){
				c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setDescripcion(rs.getString("descripcion"));
				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una categoria por id",Level.ERROR);
		}
		
		finally{
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception sqlex) {
			throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
		}
		}
		return c;
	}
	
	public Categoria getOne(String descripcion)throws SQLException,AppDataException{
		Categoria c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from categoria where descripcion=?");
			pstmt.setString(1,descripcion);
			rs= pstmt.executeQuery();
			if(rs != null && rs.next()){
				c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setDescripcion(rs.getString("descripcion"));
				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una categoria por descripcion",Level.ERROR);
		}
		
		finally{
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (Exception sqlex) {
			throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
		}
		}
		return c;
	}

	//SI EL PARAMETRO ES ENTERO
	public Categoria getOne(int idcat)throws SQLException,AppDataException{
		Categoria c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from categoria where id_categoria=?");
			pstmt.setInt(1,idcat);
			rs= pstmt.executeQuery();
			if(rs != null && rs.next()){
				c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una categoria por id",Level.ERROR);
		}		
		finally{
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
		} }
		return c;		
	}
	
	
	public void add(Categoria cat)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet keyResulset=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into categoria(descripcion) values(?);",PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,cat.getDescripcion());
			pstmt.executeUpdate();
			keyResulset=pstmt.getGeneratedKeys();					
			if(keyResulset!=null && keyResulset.next()==true){
				cat.setId(keyResulset.getInt(1));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al agregar categoria",Level.ERROR);
		}
		finally{
				try{
					if(keyResulset!=null)keyResulset.close();
					if(pstmt!=null)pstmt.close();
					FactoryConexion.getInstancia().releaseConn();}
				catch(SQLException sqlex){
					throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o preparedstatement",Level.ERROR);
		}
	  }
	}
	
	
	public void update(Categoria cat)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "update categoria set descripcion=? where id_categoria=?;");
			pstmt.setString(1, cat.getDescripcion());
			pstmt.setInt(2, cat.getId());
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar categoria",Level.ERROR);
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
	
	public void delete(Categoria cat)throws SQLException,AppDataException{
	    PreparedStatement pstmt1=null;
	    PreparedStatement pstmt2=null;
	    PreparedStatement pstmt3=null;
		try{
			pstmt3=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from reserva "
					+ "where id_persona in ( "
					+ "                     select p.id_persona "
					+ "                     from persona p "
					+ "                     where p.id_categoria=?);");
			pstmt3.setInt(1, cat.getId());
			pstmt3.executeUpdate();
			
			pstmt2=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from persona where id_categoria=?;");
			pstmt2.setInt(1, cat.getId());
			pstmt2.executeUpdate();
			
			pstmt1=FactoryConexion.getInstancia().getConn().prepareStatement("delete from categoria where id_categoria=?");
			pstmt1.setInt(1, cat.getId());
			pstmt1.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al borrar categoria",Level.ERROR);
		}
		finally{
			try{
				if(pstmt1!=null){pstmt1.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(pstmt3!=null){pstmt2.close();}
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement",Level.ERROR);
			}
		}
	}
}