package data;

import java.util.ArrayList;
import java.sql.*;

import business.entities.*;
import tools.AppDataException;


public class DataPersona{
	
	public ArrayList<Persona> getAll() throws SQLException,AppDataException{
		Statement stmt = null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		DataCategoria dc = new DataCategoria();
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			
			rs = stmt.executeQuery("select * from persona"); 	
					if(rs!=null){
						while(rs.next()){
							Persona p= new Persona();
							p.setId(rs.getInt("id_persona"));
							p.setNombre(rs.getString("nombre"));
							p.setApellido(rs.getString("apellido"));
							p.setDni(rs.getString("dni"));
							p.setUsuario(rs.getString("usuario"));		
							p.setContrasenia(rs.getString("contrasenia"));	//NO DEBER�A SER ALGUN METODO DE CONTRASE�AS?							
							p.setEmail(rs.getString("email"));
							p.setHabilitado(rs.getBoolean("habilitado"));		
							int idCat= rs.getInt("id_categoria");
							p.setCategoria(dc.getOne(idCat));
							pers.add(p);
						}
					}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al recuperar todas las personas");
		}
		    finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
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
					+ " Verifique que el usuario y/o DNI no existan, dichos registros deben ser unicos.");
		}
		finally{
			try {
				if(keyResultSet!=null) keyResultSet.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
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
			throw new AppDataException(sqlex,"Error al modificar persona. "
					+ " Verifique que el usuario y/o DNI no existan, dichos registros deben ser unicos. "
					+ " En caso de no poder resolverlo contactese con Patalalas S.A.");
		} 
		finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion de update, statement");
			} 
		}	
	}

	
	public void delete(Persona p) throws SQLException,AppDataException{	/////////////preguntar si baja logica o baja fisica///////////////////////
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
			throw new AppDataException(sqlex, "Error al eliminar persona"
											+ " En caso de no poder resolverlo contactese con Patalalas S.A.");
		}
		finally{
			if(pstmt1!=null){pstmt1.close();}
			if(pstmt2!=null){pstmt2.close();}
			FactoryConexion.getInstancia().releaseConn();
		}
	}
	
	
	public Persona getByDni(Persona per) throws SQLException,AppDataException{
		Persona p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataCategoria dc = new DataCategoria();
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona where dni=?");
			pstmt.setString(1, per.getDni());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("id_persona"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setUsuario(rs.getString("usuario"));		
				p.setContrasenia(rs.getString("contrasenia"));	//NO DEBERIA SER ALGUN METODO DE CONTRASEnia?							
				p.setEmail(rs.getString("email"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				int idCat= rs.getInt("id_categoria");
				p.setCategoria(dc.getOne(idCat));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por dni.");
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
			}
		}
		return p;
	}	
	
	public Persona getOne(Persona per) throws SQLException,AppDataException{
		Persona p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataCategoria dc = new DataCategoria();
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona where id_persona=?");
			pstmt.setInt(1, per.getId());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("id_persona"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setUsuario(rs.getString("usuario"));		
				p.setContrasenia(rs.getString("contrasenia"));						
				p.setEmail(rs.getString("email"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				int idCat= rs.getInt("id_categoria");
				p.setCategoria(dc.getOne(idCat));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por id");
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
			}
		}
		return p;
	}	
	
	public Persona getOne(int id) throws SQLException,AppDataException{
		Persona p = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataCategoria dc = new DataCategoria();
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona where id_persona=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				p= new Persona();
				p.setId(rs.getInt("id_persona"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setUsuario(rs.getString("usuario"));		
				p.setContrasenia(rs.getString("contrasenia"));							
				p.setEmail(rs.getString("email"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				int idCat= rs.getInt("id_categoria");
				p.setCategoria(dc.getOne(idCat));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una persona por id");
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
			}
		}
		return p;
	}	
		

	
	
	
	public Persona getLoggedUser(String usuario,String pass)throws SQLException,AppDataException{
		Persona per=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona where usuario=? and contrasenia=?;");
			pstmt.setString(1, usuario);
			pstmt.setString(2, pass);
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				per=new Persona();
				per.setId(res.getInt("id_persona"));
				per.setDni(res.getString("dni"));
				per.setNombre(res.getString("nombre"));
				per.setApellido(res.getString("apellido"));
				per.setUsuario(res.getString("usuario"));
				per.setContrasenia(res.getString("contrasenia"));
				per.setHabilitado(res.getBoolean("habilitado"));
				int id_categoria=res.getInt("id_categoria");
				per.setCategoria(new DataCategoria().getOne(id_categoria));
				per.setEmail(res.getString("email"));
			}
			
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar usuario logueado");
		}
		finally{
			try{
			if(res!=null)res.close();
			if(pstmt!=null)pstmt.close();
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
			}
		}
		return per;
	}
	
}