package data;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import java.sql.*;
import business.entities.Elemento;
import business.entities.TipoDeElemento;
import tools.AppDataException;
import tools.Campo;


public class DataElemento {

	public ArrayList<Elemento> getSome(Campo.TipoBusquedaE tipob,Elemento elemento)throws Exception,SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
			
			switch(tipob){
			case POR_ID:
						pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento e "
						+ "inner join tipodeelemento te "
						+ "on e.id_tipodeelemento=te.id_tipodeelemento "
						+ "where e.id_elemento=? ");
						pstmt.setInt(1, elemento.getId_elemento());
						break;
			case POR_NOMBRE:
							String nombre=elemento.getNombre();
							if(nombre==null || nombre.isEmpty()){
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where e.nombre is null || e.nombre='' ");
							}else{
			
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where e.nombre like ? ");
								pstmt.setString(1, nombre+"%");
							}
							break;
			case POR_TIPO:
							int idTipo=elemento.getTipo().getId();
							pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
									+ "select* from elemento e "
									+ "inner join tipodeelemento te "
									+ "on e.id_tipodeelemento=te.id_tipodeelemento "
									+ "where e.id_tipodeelemento=? ");
							pstmt.setInt(1, idTipo);
							break;
			case POR_NOMBRE_Y_TIPO:
							int idTipoele=elemento.getTipo().getId();
							String nom=elemento.getNombre();
							if(nom==null || nom.isEmpty()){
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where (e.nombre is null || e.nombre='') and e.id_tipodeelemento=? ");
								pstmt.setInt(1, idTipoele);
							}
							else{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where e.nombre like ? and e.id_tipodeelemento=? ");
							pstmt.setString(1, nom+"%");
							pstmt.setInt(2, idTipoele);
							}
							break;
			case TRAER_TODOS:
			default:
					pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento e "
						+ "inner join tipodeelemento te "
						+ "on e.id_tipodeelemento=te.id_tipodeelemento "
						+ "order by e.nombre desc");
			}
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("id_elemento"));
					ele.setNombre(res.getString("nombre"));
					
					TipoDeElemento te=new TipoDeElemento();
					te.setId(res.getInt("id_tipodeelemento"));
					te.setNombre(res.getString("te.nombre"));
					te.setCant_max_res_pen(res.getInt("cantmaxrespen"));
					te.setLimite_horas_res(res.getInt("limite_horas_res"));
					te.setDias_max_anticipacion(res.getInt("dias_max_anticipacion"));
					te.setOnly_encargados(res.getBoolean("only_encargados"));
					ele.setTipo(te);
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos\n"+sqlex.getMessage(),Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement",Level.ERROR);
			}
		}
		return elementos;
	}
	
	public ArrayList<Elemento> getSome(Elemento elemento,java.util.Date fechaDisp)throws SQLException,AppDataException,Exception{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select ele.*,te.* "
					+ "from elemento ele "
					+ "left join reserva res "
					+ "on ele.id_elemento=res.id_elemento "
					+ "inner join tipodeelemento te "
					+ "on te.id_tipodeelemento=ele.id_tipodeelemento "
					+ "where te.nombre=? and ele.id_elemento not in( "
							+ "select  r.id_elemento "
							+ "from reserva r "
							+ "inner join elemento e "
							+ "on r.id_elemento = e.id_elemento "
							+ "where r.fecha_hora_desde_solicitada <= ? "
							+ "and r.fecha_hora_hasta_solicitada >= ?); "
					);
			pstmt.setString(1, elemento.getTipo().getNombre());
			pstmt.setString(2, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("ele.id_elemento"));
					ele.setNombre(res.getString("ele.nombre"));
					
					TipoDeElemento te=new TipoDeElemento();
					te.setId(res.getInt("te.id_tipodeelemento"));
					te.setNombre(res.getString("te.nombre"));
					te.setCant_max_res_pen(res.getInt("te.cantmaxrespen"));
					te.setLimite_horas_res(res.getInt("te.limite_horas_res"));
					te.setDias_max_anticipacion(res.getInt("te.dias_max_anticipacion"));
					te.setOnly_encargados(res.getBoolean("only_encargados"));
					ele.setTipo(te);
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer elementos de un tipo disponibles en una fecha",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar PreparedStatement,ResultSet o Conexion",Level.ERROR);
			}
		}
		return elementos;
	}
	
	


	public Elemento getOne(Elemento elem) throws Exception,SQLException, AppDataException{
		Elemento e =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ " select e.*,t.* from elemento e"
					+ " inner join tipodeelemento t"
					+ " on e.id_tipodeelemento=t.id_tipodeelemento"
					+ " where e.id_elemento=?");
			pstmt.setInt(1,elem.getId_elemento());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				e = new Elemento();
				e.setId_elemento(rs.getInt("e.id_elemento"));
				e.setNombre(rs.getString("e.nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setId(rs.getInt("t.id_tipodeelemento"));
				te.setNombre(rs.getString("t.nombre"));
				te.setCant_max_res_pen(rs.getInt("t.cantmaxrespen"));
				te.setLimite_horas_res(rs.getInt("t.limite_horas_res"));
				te.setDias_max_anticipacion(rs.getInt("t.dias_max_anticipacion"));
				te.setOnly_encargados(rs.getBoolean("t.only_encargados"));
				e.setTipo(te);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar un Elemento",Level.ERROR);
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar resultset,preparedstatement o conexion en busqueda de elemento",Level.ERROR);
			}
		}
		
		return e;
	}
	
	
	public Elemento getOne(int id_elem_p) throws SQLException, AppDataException{
		Elemento e =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ " select e.*,t.* from elemento e"
					+ " inner join tipodeelemento t"
					+ " on e.id_tipodeelemento=t.id_tipodeelemento"
					+ " where e.id_elemento=?");
			pstmt.setInt(1,id_elem_p);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				e = new Elemento();
				e.setId_elemento(rs.getInt("e.id_elemento"));
				e.setNombre(rs.getString("e.nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setId(rs.getInt("t.id_tipodeelemento"));
				te.setNombre(rs.getString("t.nombre"));
				te.setCant_max_res_pen(rs.getInt("t.cantmaxrespen"));
				te.setLimite_horas_res(rs.getInt("t.limite_horas_res"));
				te.setDias_max_anticipacion(rs.getInt("t.dias_max_anticipacion"));
				te.setOnly_encargados(rs.getBoolean("t.only_encargados"));
				e.setTipo(te);
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una Elemento",Level.ERROR);
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar resultset,preparedstatement o conexion en busqueda de elemento",Level.ERROR);
			}
		}
		return e;
	}
	
	public void add(Elemento ele)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet KeyRes=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement("insert into elemento(nombre,id_tipodeelemento) values(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ele.getNombre());
			pstmt.setInt(2,ele.getTipo().getId());
			pstmt.executeUpdate();
			KeyRes=pstmt.getGeneratedKeys();
			if(KeyRes!=null && KeyRes.next()){
				ele.setId_elemento(KeyRes.getInt(1));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al agregar elemento",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(KeyRes!=null){KeyRes.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion, resultset o statement",Level.ERROR);
			}
			
		}
	}
	
	public void delete(Elemento ele)throws SQLException,AppDataException{
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try{
			pstmt1=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from reserva where id_elemento=?;");
			pstmt1.setInt(1, ele.getId_elemento());
			pstmt1.executeUpdate();
			
			pstmt2=FactoryConexion.getInstancia().getConn().prepareStatement("delete from elemento where id_elemento=?");
			pstmt2.setInt(1, ele.getId_elemento());
			pstmt2.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al borrar elemento",Level.ERROR);
		}
		finally{
			try{
				if(pstmt1!=null){pstmt1.close();}
				if(pstmt2!=null){pstmt2.close();}
				FactoryConexion.getInstancia().releaseConn();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement",Level.ERROR);
			}
		}
	}
	
	public void update(Elemento ele)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement("update elemento set nombre=?,id_tipodeelemento=? where id_elemento=? ;");
			pstmt.setString(1, ele.getNombre());
			pstmt.setInt(2,ele.getTipo().getId());
			pstmt.setInt(3,ele.getId_elemento());
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar elemento",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement",Level.ERROR);}
		}
	}
	
	public int getMaxId()throws SQLException,AppDataException{
		int id=-1;
		Statement stmt=null;
		ResultSet res=null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().createStatement();
			res=stmt.executeQuery("select max(id_elemento) from elemento;");
			if(res.next() && res!=null){
				id=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar el Id mas grande entre los elementos\n"+sqlex.getMessage(),Level.ERROR);
		}
		finally{
			try{
			if(stmt!=null){stmt.close();}
			if(res!=null){stmt.close();}
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,sqlex.getMessage(),Level.ERROR);
			}
		}
		return id;
		
	}
}
