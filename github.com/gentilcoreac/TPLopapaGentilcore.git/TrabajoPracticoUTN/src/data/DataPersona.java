package data;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import java.sql.*;

import business.entities.*;
import tools.AppDataException;


public class DataPersona{
	
	public ArrayList<Persona> getAll() throws SQLException,AppDataException{
		Statement stmt = null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			
			rs = stmt.executeQuery(""
					+ "select p.*,c.* from persona p "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria"); 	
					if(rs!=null){
						while(rs.next()){
							Persona p= new Persona();
							p.setId(rs.getInt("p.id_persona"));
							p.setNombre(rs.getString("p.nombre"));
							p.setApellido(rs.getString("p.apellido"));
							p.setDni(rs.getString("p.dni"));
							p.setUsuario(rs.getString("p.usuario"));		
							p.setContrasenia(rs.getString("p.contrasenia"));								
							p.setEmail(rs.getString("p.email"));
							p.setHabilitado(rs.getBoolean("p.habilitado"));
							Categoria cat=new Categoria();
							cat.setId(rs.getInt("c.id_categoria"));
							cat.setDescripcion(rs.getString("c.descripcion"));
							p.setCategoria(cat);
							pers.add(p);
						}
					}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al recuperar todas las personas",Level.ERROR);
		}
		    finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
			}
		}
		return pers;
	}
	

	public void add(Persona p)throws SQLException,AppDataException{
		PreparedStatement pstmt = null;
		ResultSet keyResultSet = null;
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into persona(dni, nombre, apellido, usuario, contrasenia, email, habilitado, id_categoria) values(?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			pstmt.setString(1, p.getDni());
			pstmt.setString(2, p.getNombre());
			pstmt.setString(3, p.getApellido());
			pstmt.setString(4, p.getUsuario());
			pstmt.setString(5, p.getContrasenia());
			pstmt.setString(6, p.getEmail());			
			pstmt.setBoolean(7, p.isHabilitado());
			pstmt.setInt(8, p.getCategoria().getId());
			pstmt.execute();
			keyResultSet = pstmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al agregar persona.\n"
					+ "Verifique que el usuario y/o DNI no existan, dichos registros deben ser unicos.",Level.ERROR);
		}
		finally{
			try {
				if(keyResultSet!=null) keyResultSet.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
			}
		}
	}
	
	
	
	public void update(Persona p) throws SQLException,AppDataException{
	PreparedStatement stmt=null;
		
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"update persona set dni=?, nombre=?, apellido=?, usuario=?, contrasenia=?, email=?, habilitado=? , id_categoria=?"+
					" where dni=?");
			
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getApellido());
			stmt.setString(4, p.getUsuario());
			stmt.setString(5, p.getContrasenia());
			stmt.setString(6, p.getEmail());	
			stmt.setBoolean(7, p.isHabilitado());
			stmt.setInt(8, p.getCategoria().getId());
			stmt.setString(9, p.getDni());
			int rowsAffected=stmt.executeUpdate();
			if(rowsAffected==0){throw new AppDataException(new Exception("Persona Inexistente, no se pudo actualizar"),"Error");}
			
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al modificar persona."
					+ "Verifique que el usuario y/o DNI no existan, dichos registros deben ser unicos.",Level.ERROR);
		} 
		finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion de update, statement",Level.ERROR);
			} 
		}	
	}

	
	public void delete(Persona p) throws SQLException,AppDataException{	
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt1=FactoryConexion.getInstancia().getConn().prepareStatement(
					"delete from reserva "
					+ "where id_persona in "
					+ "(select id_persona from persona where dni=?);");
			pstmt1.setString(1, p.getDni());
			pstmt1.executeUpdate();
			pstmt2=FactoryConexion.getInstancia().getConn().prepareStatement("delete from persona where dni=?;");
			pstmt2.setString(1,p.getDni());
			int rowsAffected=pstmt2.executeUpdate();
			if(rowsAffected==0){
				throw new AppDataException(new Exception("Persona inexistente\nNo se pudo eliminar"),"Error");
			}
			
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al eliminar persona",Level.ERROR);
		}
		finally{
			try{
				if(pstmt1!=null){pstmt1.close();}
				if(pstmt2!=null){pstmt2.close();}
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex, "Error al cerrar conexion o preparedstatement de delete",Level.ERROR);
			}
		}
	}
	
	
	public Persona getByDni(Persona per) throws SQLException,AppDataException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Persona p=null;
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select p.*,c.* from persona p "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria "
					+ "where p.dni=?");
			pstmt.setString(1, per.getDni());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("p.id_persona"));
				p.setNombre(rs.getString("p.nombre"));
				p.setApellido(rs.getString("p.apellido"));
				p.setDni(rs.getString("p.dni"));
				p.setUsuario(rs.getString("p.usuario"));		
				p.setContrasenia(rs.getString("p.contrasenia"));								
				p.setEmail(rs.getString("p.email"));
				p.setHabilitado(rs.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(rs.getInt("c.id_categoria"));
				cat.setDescripcion(rs.getString("c.descripcion"));
				p.setCategoria(cat);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por dni.",Level.ERROR);
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o preparedstatement",Level.ERROR);
			}
		}
		return p;
	}	
	
	public Persona getOne(Persona per) throws SQLException,AppDataException{
		Persona p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select p.*,c.* from persona p "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria "
					+ "where p.id_persona=?");
			pstmt.setInt(1, per.getId());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("p.id_persona"));
				p.setNombre(rs.getString("p.nombre"));
				p.setApellido(rs.getString("p.apellido"));
				p.setDni(rs.getString("p.dni"));
				p.setUsuario(rs.getString("p.usuario"));		
				p.setContrasenia(rs.getString("p.contrasenia"));								
				p.setEmail(rs.getString("p.email"));
				p.setHabilitado(rs.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(rs.getInt("c.id_categoria"));
				cat.setDescripcion(rs.getString("c.descripcion"));
				p.setCategoria(cat);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por id",Level.ERROR);
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o preparedstatement",Level.ERROR);
			}
		}
		return p;
	}	
	
	public Persona getOne(int id) throws SQLException,AppDataException{
		Persona p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select p.*,c.* from persona p "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria "
					+ "where p.id_persona=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("p.id_persona"));
				p.setNombre(rs.getString("p.nombre"));
				p.setApellido(rs.getString("p.apellido"));
				p.setDni(rs.getString("p.dni"));
				p.setUsuario(rs.getString("p.usuario"));		
				p.setContrasenia(rs.getString("p.contrasenia"));								
				p.setEmail(rs.getString("p.email"));
				p.setHabilitado(rs.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(rs.getInt("c.id_categoria"));
				cat.setDescripcion(rs.getString("c.descripcion"));
				p.setCategoria(cat);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por id",Level.ERROR);
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o preparedstatement",Level.ERROR);
			}
		}
		return p;
	}	
		

	
	
	
	public Persona getLoggedUser(String usuario,String pass)throws SQLException,AppDataException{
		Persona p=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select p.*,c.* from persona p "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria "
					+ "where p.usuario=? and p.contrasenia=?;");
			pstmt.setString(1, usuario);
			pstmt.setString(2, pass);
			rs=pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("p.id_persona"));
				p.setNombre(rs.getString("p.nombre"));
				p.setApellido(rs.getString("p.apellido"));
				p.setDni(rs.getString("p.dni"));
				p.setUsuario(rs.getString("p.usuario"));		
				p.setContrasenia(rs.getString("p.contrasenia"));								
				p.setEmail(rs.getString("p.email"));
				p.setHabilitado(rs.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(rs.getInt("c.id_categoria"));
				cat.setDescripcion(rs.getString("c.descripcion"));
				p.setCategoria(cat);
			}
			
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar usuario logueado",Level.ERROR);
		}
		finally{
			try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement",Level.ERROR);
			}
		}
		return p;
	}
	
}