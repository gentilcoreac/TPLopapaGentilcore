package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.Level;

import business.entities.Categoria;
import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.entities.TipoDeElemento;
import tools.AppDataException;
import tools.Campo;


public class DataReserva {

	
	public ArrayList<Reserva> getSome(Campo.TipoBusquedaR tipob,Reserva reserva)throws Exception,SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
			switch(tipob){
			case POR_IDRESERVA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_reserva=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getId_reserva());
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_elemento=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								break;
			case POR_IDPERSONA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getPersona()!=null?reserva.getPersona().getId():-1);
								break;
			case PENDIENTES:	
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.fecha_hora_desde_solicitada > now() "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.fecha_hora_entregado is null and "
								+ "r.fecha_hora_hasta_solicitada < now() "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("e.id_elemento"));
					ele.setNombre(res.getString("e.nombre"));
					TipoDeElemento te=new TipoDeElemento();
					te.setId(res.getInt("t.id_tipodeelemento"));
					te.setNombre(res.getString("t.nombre"));
					te.setCant_max_res_pen(res.getInt("t.cantmaxrespen"));
					te.setLimite_horas_res(res.getInt("t.limite_horas_res"));
					te.setDias_max_anticipacion(res.getInt("t.dias_max_anticipacion"));
					te.setOnly_encargados(res.getBoolean("t.only_encargados"));
					ele.setTipo(te);
					
					Persona p= new Persona();
					p.setId(res.getInt("p.id_persona"));
					p.setNombre(res.getString("p.nombre"));
					p.setApellido(res.getString("p.apellido"));
					p.setDni(res.getString("p.dni"));
					p.setUsuario(res.getString("p.usuario"));		
					p.setContrasenia(res.getString("p.contrasenia"));								
					p.setEmail(res.getString("p.email"));
					p.setHabilitado(res.getBoolean("p.habilitado"));
					Categoria cat=new Categoria();
					cat.setId(res.getInt("c.id_categoria"));
					cat.setDescripcion(res.getString("c.descripcion"));
					p.setCategoria(cat);
					
					
					Reserva r=new Reserva();
					r.setId_reserva(res.getInt("r.id_reserva"));
					r.setPersona(p);
					r.setElemento(ele);
					r.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					r.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					r.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					r.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					r.setDetalle(res.getString("r.detalle"));
					
					reservas.add(r);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas",Level.ERROR);
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
		return reservas;
	}
	
	
	public ArrayList<Reserva> getSome(Persona persona,Campo.TipoBusquedaR tipob,Reserva reserva)throws Exception,SQLException,AppDataException{
		
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
			switch(tipob){
			case POR_IDRESERVA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_reserva=? and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getId_reserva());
								pstmt.setInt(2, persona.getId());
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_elemento=? and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								pstmt.setInt(2, persona.getId());
								break;
			case PENDIENTES:	
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.fecha_hora_desde_solicitada > now() and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, persona.getId());
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.fecha_hora_entregado is null and "
								+ "r.fecha_hora_hasta_solicitada < now() and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, persona.getId());
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join tipodeelemento t "
								+ "on t.id_tipodeelemento=e.id_tipodeelemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "inner join categoria c "
								+ "on p.id_categoria=c.id_categoria "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, persona.getId());
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("e.id_elemento"));
					ele.setNombre(res.getString("e.nombre"));
					TipoDeElemento te=new TipoDeElemento();
					te.setId(res.getInt("t.id_tipodeelemento"));
					te.setNombre(res.getString("t.nombre"));
					te.setCant_max_res_pen(res.getInt("t.cantmaxrespen"));
					te.setLimite_horas_res(res.getInt("t.limite_horas_res"));
					te.setDias_max_anticipacion(res.getInt("t.dias_max_anticipacion"));
					te.setOnly_encargados(res.getBoolean("t.only_encargados"));
					ele.setTipo(te);
					
					Persona p= new Persona();
					p.setId(res.getInt("p.id_persona"));
					p.setNombre(res.getString("p.nombre"));
					p.setApellido(res.getString("p.apellido"));
					p.setDni(res.getString("p.dni"));
					p.setUsuario(res.getString("p.usuario"));		
					p.setContrasenia(res.getString("p.contrasenia"));								
					p.setEmail(res.getString("p.email"));
					p.setHabilitado(res.getBoolean("p.habilitado"));
					Categoria cat=new Categoria();
					cat.setId(res.getInt("c.id_categoria"));
					cat.setDescripcion(res.getString("c.descripcion"));
					p.setCategoria(cat);
					
					
					Reserva r=new Reserva();
					r.setId_reserva(res.getInt("r.id_reserva"));
					r.setPersona(p);
					r.setElemento(ele);
					r.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					r.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					r.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					r.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					r.setDetalle(res.getString("r.detalle"));
					
					reservas.add(r);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas\n"+sqlex.getMessage(),Level.ERROR);
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
		return reservas;
	}
	
	
	
	
	public void add(Reserva r)throws SQLException,AppDataException{
		PreparedStatement pstmt = null;
		ResultSet keyResultSet = null;
		try {
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "insert into reserva("
						+ " id_persona, "
						+ " id_elemento, "
						+ " fecha_hora_reserva_hecha,"
						+ " fecha_hora_desde_solicitada,"
						+ " fecha_hora_hasta_solicitada, "
					//	+ " fecha_hora_entregado, "				//este campo solo se usa en update cuando el administrador registra la devolucion
						+ " detalle) "
					+ "values(?,?,?,?,?,?); "
						,PreparedStatement.RETURN_GENERATED_KEYS);
		
			pstmt.setInt(1,r.getPersona().getId());
			pstmt.setInt(2, r.getElemento().getId_elemento());
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(r.getFecha_hora_reserva_hecha()));		
			pstmt.setString(4,new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( r.getFecha_hora_desde_solicitada()));
			pstmt.setString(5, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( r.getFecha_hora_hasta_solicitada()));
		    pstmt.setString(6,r.getDetalle());
			pstmt.executeUpdate();							
			keyResultSet = pstmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId_reserva(keyResultSet.getInt(1));				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al crear reserva",Level.ERROR);
		}
		finally{
			try {
				if(keyResultSet!=null) keyResultSet.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o preparedstatement",Level.ERROR);
			}
		}
	}	
	
	
	public void delete(Reserva r)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from reserva where id_reserva=?");
			pstmt.setInt(1,r.getId_reserva());
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al intentar borrar reserva",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){}pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar PreparedStatement o Conexion",Level.ERROR);
			}
		}
	}
	
	
	
	public void updateParaCerrarRes(Reserva r)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;					
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""	
					+ " update reserva "
					+ " set fecha_hora_entregado=? "
					+ " where id_reserva=? ");

			pstmt.setString(1,  new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(r.getFecha_hora_entregado()));	
			pstmt.setInt(2, r.getId_reserva());
			
		
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar reserva para cerrarla",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion o PreparedStatement",Level.ERROR);
			}
		}
	}
	
	
	
	public Reserva getOne(Reserva reser)throws Exception{
		Reserva r=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select* from reserva r "
					+ "inner join elemento e "
					+ "on e.id_elemento=r.id_elemento "
					+ "inner join tipodeelemento t "
					+ "on t.id_tipodeelemento=e.id_tipodeelemento "
					+ "inner join persona p on p.id_persona=r.id_persona "
					+ "inner join categoria c "
					+ "on p.id_categoria=c.id_categoria "
					+ "where r.id_reserva=?;");
			pstmt.setInt(1, reser.getId_reserva());
			res=pstmt.executeQuery();
			if(res.next() && res!=null){
				Elemento ele=new Elemento();
				ele.setId_elemento(res.getInt("e.id_elemento"));
				ele.setNombre(res.getString("e.nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setId(res.getInt("t.id_tipodeelemento"));
				te.setNombre(res.getString("t.nombre"));
				te.setCant_max_res_pen(res.getInt("t.cantmaxrespen"));
				te.setLimite_horas_res(res.getInt("t.limite_horas_res"));
				te.setDias_max_anticipacion(res.getInt("t.dias_max_anticipacion"));
				te.setOnly_encargados(res.getBoolean("t.only_encargados"));
				ele.setTipo(te);
				
				Persona p= new Persona();
				p.setId(res.getInt("p.id_persona"));
				p.setNombre(res.getString("p.nombre"));
				p.setApellido(res.getString("p.apellido"));
				p.setDni(res.getString("p.dni"));
				p.setUsuario(res.getString("p.usuario"));		
				p.setContrasenia(res.getString("p.contrasenia"));								
				p.setEmail(res.getString("p.email"));
				p.setHabilitado(res.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(res.getInt("c.id_categoria"));
				cat.setDescripcion(res.getString("c.descripcion"));
				p.setCategoria(cat);
				
				
				r=new Reserva();
				r.setId_reserva(res.getInt("r.id_reserva"));
				r.setPersona(p);
				r.setElemento(ele);
				r.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
				r.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
				r.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
				r.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
				r.setDetalle(res.getString("r.detalle"));
				
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar una reserva",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion,ResultSet o PreparedStatement",Level.ERROR);
			}
		}
		return r;
	}
	
	public Reserva getOne(int idr,Persona per)throws Exception{
		Reserva r=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select * from reserva r "
					+ "where r.id_reserva=? and r.id_persona=?;");
			pstmt.setInt(1, idr);
			pstmt.setInt(2, per.getId());
			res=pstmt.executeQuery();
			if(res.next() && res!=null){
				Elemento ele=new Elemento();
				ele.setId_elemento(res.getInt("e.id_elemento"));
				ele.setNombre(res.getString("e.nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setId(res.getInt("t.id_tipodeelemento"));
				te.setNombre(res.getString("t.nombre"));
				te.setCant_max_res_pen(res.getInt("t.cantmaxrespen"));
				te.setLimite_horas_res(res.getInt("t.limite_horas_res"));
				te.setDias_max_anticipacion(res.getInt("t.dias_max_anticipacion"));
				te.setOnly_encargados(res.getBoolean("t.only_encargados"));
				ele.setTipo(te);
				
				Persona p= new Persona();
				p.setId(res.getInt("p.id_persona"));
				p.setNombre(res.getString("p.nombre"));
				p.setApellido(res.getString("p.apellido"));
				p.setDni(res.getString("p.dni"));
				p.setUsuario(res.getString("p.usuario"));		
				p.setContrasenia(res.getString("p.contrasenia"));								
				p.setEmail(res.getString("p.email"));
				p.setHabilitado(res.getBoolean("p.habilitado"));
				Categoria cat=new Categoria();
				cat.setId(res.getInt("c.id_categoria"));
				cat.setDescripcion(res.getString("c.descripcion"));
				p.setCategoria(cat);
				
				
				r=new Reserva();
				r.setId_reserva(res.getInt("r.id_reserva"));
				r.setPersona(p);
				r.setElemento(ele);
				r.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
				r.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
				r.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
				r.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
				r.setDetalle(res.getString("r.detalle"));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar una reserva",Level.ERROR);
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion,ResultSet o PreparedStatement",Level.ERROR);
			}
		}
		return r;
	}

	
	public int getMaxId()throws SQLException,AppDataException{
		int id=-1;
		Statement stmt=null;
		ResultSet res=null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().createStatement();
			res=stmt.executeQuery("select max(id_reserva) from reserva;");
			if(res.next() && res!=null){
				id=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar el Id mas grande entre las reservas\n"+sqlex.getMessage(),Level.ERROR);
		}
		finally{
			try{
				if(stmt!=null){stmt.close();}
				if(res!=null){stmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,sqlex.getMessage(),Level.ERROR);
			}
		}
		return id;
		
	}
	   
	public int getReservasEnIntervalo(int idEle,Date fechaD,Date fechaH)throws AppDataException,SQLException,Exception{
		int intersecciones=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) from reserva r "
					+ "where r.id_elemento=? and "
					+ "not (r.fecha_hora_desde_solicitada<? and r.fecha_hora_hasta_solicitada<=?) " 
					+ "and not (r.fecha_hora_desde_solicitada>=? and r.fecha_hora_hasta_solicitada>?);");
			pstmt.setInt(1, idEle);
			pstmt.setString(2, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaD));
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaD));
			pstmt.setString(4, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaH));
			pstmt.setString(5, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaH));
			res=pstmt.executeQuery();
		
			if(res!=null && res.next()){
				intersecciones=res.getInt(1);
			}
		
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al verificar si se puede reservar en el intervalo fechaDesde-fechaHasta",Level.ERROR);
		}
		finally{
			try{
			if(pstmt!=null){pstmt.close();}
			if(res!=null){res.close();}
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion,resultset o preparedstatement",Level.ERROR);
			}
		}
		return intersecciones;
	}
	
	public int getNumResPen(Persona persona,Reserva reserva)throws Exception{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) "
					+ "from reserva res "
					+ "inner join elemento ele "
					+ "on res.id_elemento=ele.id_elemento "
					+ "inner join tipodeelemento tde "
					+ "on ele.id_tipodeelemento=tde.id_tipodeelemento "
					+ "where res.id_persona=? and res.fecha_hora_desde_solicitada>now() "
					+ "and tde.id_tipodeelemento=?;");
			pstmt.setInt(1,persona.getId());
			pstmt.setInt(2, reserva.getElemento().getTipo().getId());
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar numero de reservas pendientes",Level.ERROR);
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
		return cantidad;
	}
   
}
