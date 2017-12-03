package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.entities.TipoDeElemento;
import tools.AppDataException;
import tools.Campo;


public class DataReserva {

	
	public ArrayList<Reserva> getAll()throws Exception,SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "order by r.fecha_hora_reserva_hecha desc ");
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer todas las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return reservas;
	}

	/*
	public ArrayList<Reserva> getSome(Campo.TipoBusquedaR tipob,Reserva reserva,int indice,int cantTraer)throws Exception,SQLException,AppDataException{
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
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_reserva=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getId_reserva());
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_elemento=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case POR_IDPERSONA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getPersona()!=null?reserva.getPersona().getId():-1);
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case PENDIENTES:	
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where datediff(r.fecha_hora_desde_solicitada,now())>0 "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, indice);
								pstmt.setInt(2, cantTraer);
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.fecha_hora_entregado is null and "
								+ "datediff(r.fecha_hora_hasta_solicitada,now())<0 "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, indice);
								pstmt.setInt(2, cantTraer);
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, indice);
								pstmt.setInt(2, cantTraer);
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return reservas;
	}
	
	public ArrayList<Reserva> getSome(Persona persona,Campo.TipoBusquedaR tipob,Reserva reserva,int indice,int cantTraer)throws Exception,SQLException,AppDataException{
		
		//persona es siempre la entro al sistema
		//puede ser diferente a la que esta en la reserva pasada como parametro
		//por ejemplo un administrador busca las reservas de un encargado, 
		//ahi persona y reserva.getpreserona son diferentes
		//aca no cambia nada pero en el futuro puede ser util
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
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_reserva=? and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getId_reserva());
								pstmt.setInt(2, persona.getId());
								pstmt.setInt(3, indice);
								pstmt.setInt(4, cantTraer);
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_elemento=? and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								pstmt.setInt(2, persona.getId());
								pstmt.setInt(3, indice);
								pstmt.setInt(4, cantTraer);
								break;
			case POR_IDPERSONA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, reserva.getPersona()!=null?reserva.getPersona().getId():-1);
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case PENDIENTES:	
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where datediff(r.fecha_hora_desde_solicitada,now())>0 and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, persona.getId());
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.fecha_hora_entregado is null and "
								+ "datediff(r.fecha_hora_hasta_solicitada,now())<0 and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, persona.getId());
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc "
								+ "limit ?,?");
								pstmt.setInt(1, persona.getId());
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return reservas;
	}
	*/
	
	
	public ArrayList<Reserva> getSome(Reserva reserva)throws Exception,SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select* from reserva r "
								+ "inner join elemento e "
								+ "on e.id_elemento=r.id_elemento "
								+ "inner join persona p on p.id_persona=r.id_persona "
								+ "where r.id_reserva=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getId_reserva());
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return reservas;
	}

	public ArrayList<Reserva> getSome(Persona persona,Reserva reserva)throws Exception,SQLException,AppDataException{
		
		//persona es siempre la q entro al sistema
		//puede ser diferente a la que esta en la reserva pasada como parametro
		//por ejemplo un administrador busca las reservas de un encargado, 
		//ahi persona y reserva.getpreserona son diferentes
		//aca no cambia nada pero en el futuro puede ser util
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select * "
								+ "from reserva r "
								+ "inner join elemento e   on e.id_elemento=r.id_elemento "
								+ "inner join persona p    on p.id_persona=r.id_persona "
								+ "where r.id_reserva=? and r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, reserva.getId_reserva());
								pstmt.setInt(2, persona.getId());	
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
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
					//	+ " fecha_hora_entregado, "				//este solo se usa para cuando el administrador, o quien sea, registre que se devolvi�
						+ " detalle) "
					+ "values(?,?,?,?,?,?); "
						,PreparedStatement.RETURN_GENERATED_KEYS);
		
			pstmt.setInt(1,r.getPersona().getId());
			pstmt.setInt(2, r.getElemento().getId_elemento());
			//pstmt.setString(3, String.valueOf(new java.sql.Date(r.getFecha_hora_reserva_hecha().getTime())));
			//pstmt.setString(3, String.valueOf(r.getFecha_hora_reserva_hecha()));
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(r.getFecha_hora_reserva_hecha()));		
			pstmt.setString(4,new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( r.getFecha_hora_desde_solicitada()));
			pstmt.setString(5, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( r.getFecha_hora_hasta_solicitada()));
		//	pstmt.setDate(5, (java.sql.Date)r.getFecha_hora_entregado());     //este solo se usa para cuando el administrador, o quien sea, registre que se devolvi�
			pstmt.setString(6,r.getDetalle());
			pstmt.executeUpdate();							//execute= ejecuta todo      /executequery solo consultas select   /executeupdate solo add update o delete
			keyResultSet = pstmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId_reserva(keyResultSet.getInt(1));				
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex,"Error al crear reserva");
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
	
	/*
	public int getCantidad(Campo.TipoBusquedaR tipob,Reserva reserva)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		int cantidad=0;
		try{
			switch(tipob){
			case POR_IDRESERVA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_reserva=? ");
								pstmt.setInt(1, reserva.getId_reserva());
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_elemento=? ");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								break;
			case POR_IDPERSONA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_persona=? ");
								pstmt.setInt(1, reserva.getPersona()!=null?reserva.getPersona().getId():-1);
								break;
			case PENDIENTES:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where datediff(r.fecha_hora_desde_solicitada,now())>0 ");
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.fecha_hora_entregado is null and "
								+ "datediff(r.fecha_hora_hasta_solicitada,now())<0 ");
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva ");
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return cantidad;
	}
	
	public int getCantidad(Persona persona,Campo.TipoBusquedaR tipob,Reserva reserva)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		int cantidad=0;
		try{
			switch(tipob){
			case POR_IDRESERVA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_reserva=? and r.id_persona=? ");
								pstmt.setInt(1, reserva.getId_reserva());
								pstmt.setInt(2, persona.getId());
								break;
			case POR_IDELEMENTO:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_elemento=?  and r.id_persona=? ");
								pstmt.setInt(1, reserva.getElemento()!=null?reserva.getElemento().getId_elemento():-1);
								pstmt.setInt(2, persona.getId());
								break;
			case POR_IDPERSONA:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_persona=? ");
								pstmt.setInt(1, reserva.getPersona()!=null?reserva.getPersona().getId():-1);
								break;
			case PENDIENTES:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where datediff(r.fecha_hora_desde_solicitada,now())>0  and r.id_persona=? ");
								pstmt.setInt(1, persona.getId());
								break;
			case VENCIDAS:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.fecha_hora_entregado is null and "
								+ "datediff(r.fecha_hora_hasta_solicitada,now())<0  and r.id_persona=? ");
								pstmt.setInt(1, persona.getId());
								break;
			case TRAER_TODAS:
			default:
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from reserva r "
								+ "where r.id_persona=? ");
								pstmt.setInt(1, persona.getId());
								break;
			}
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return cantidad;
	}
	
	*/
	
	
	public void delete(Reserva r)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "delete from reserva where id_reserva=?");
			pstmt.setInt(1,r.getId_reserva());
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al intentar borrar reserva");
		}
		finally{
			try{
				if(pstmt!=null){}pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar PreparedStatement o Conexion");
			}
		}
	}
	
	
	
	/*
	public void update(Reserva r)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "update reserva set fecha_hora_reserva_hecha=?,"
					+ "set fecha_hora_desde_solicitada=?,set fecha_hora_hasta_solicitada=?,"
					+ "set fecha_hora_entregado=?,set detalle=?");
			pstmt.executeUpdate();
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al modificar reserva");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion o PreparedStatement");
			}
		}
	}
	*/
	
	//METODO PARA "ESTABLECER" LA FECHA DE DE FIN RESERVAS, ES DECIR, DAR POR FINALIZADA LA RESERVA
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
			throw new AppDataException(sqlex,"Error al modificar reserva");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion o PreparedStatement");
			}
		}
	}
	
	
	
	public Reserva getOne(Reserva r)throws Exception{
		Reserva reserva=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select * from reserva r where id_reserva=?;");
			pstmt.setInt(1, r.getId_reserva());
			rs=pstmt.executeQuery();
			if(rs.next() && rs!=null){
				reserva=new Reserva();
				reserva.setId_reserva(rs.getInt("r.id_reserva"));
				reserva.setPersona(new DataPersona().getOne(rs.getInt("r.id_persona")));
				reserva.setElemento(new DataElemento().getOne(rs.getInt("r.id_elemento")));
				java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				reserva.setFecha_hora_reserva_hecha(rs.getString("r.fecha_hora_reserva_hecha")!=null?formatter.parse(rs.getString("r.fecha_hora_reserva_hecha")):null);
				reserva.setFecha_hora_desde_solicitada(rs.getString("r.fecha_hora_desde_solicitada")!=null?formatter.parse(rs.getString("r.fecha_hora_desde_solicitada")):null);
				reserva.setFecha_hora_hasta_solicitada(rs.getString("r.fecha_hora_hasta_solicitada")!=null?formatter.parse(rs.getString("r.fecha_hora_hasta_solicitada")):null);
				reserva.setFecha_hora_entregado(rs.getString("r.fecha_hora_entregado")!=null?formatter.parse(rs.getString("r.fecha_hora_entregado")):null);
				reserva.setDetalle(rs.getString("detalle"));
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al buscar una reserva");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(rs!=null){rs.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al intentar cerrar conexion,ResultSet o PreparedStatement");
			}
		}
		return reserva;
	}
	

	//LISTADO DE RESERVAS PENDIENTES QUE TIENE UN USUARIO EN PARTICULAR
//	public ArrayList<Reserva> getPendientes(Persona p) throws Exception,SQLException,AppDataException{
//		PreparedStatement stmt = null;
//		ResultSet rs=null;
//		ArrayList<Reserva> res= new ArrayList<Reserva>();
//		DataElemento de = new DataElemento();
//		
//		try {
//			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(""
//						+ "select * "				
//						+ " from reserva "
//						+ " where fecha_hora_entregado is null 	"
//						+ "		and id_persona=?"
//						+ "		and datediff(fecha_hora_desde_solicitada,now()) > 0 ");//hasta no sera?
//			
//			stmt.setInt(1, p.getId());
//			rs = stmt.executeQuery(); 	
//					if(rs!=null){
//						while(rs.next()){
//							Reserva r= new Reserva();
//							r.setPersona(p);
//							r.setId_reserva(rs.getInt("id_reserva"));
//							int idEle= rs.getInt("id_elemento");
//							r.setElemento(de.getOne(idEle));
//							java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//							r.setFecha_hora_desde_solicitada(formatter.parse(rs.getString("fecha_hora_desde_solicitada")));
//							r.setFecha_hora_hasta_solicitada(formatter.parse(rs.getString("fecha_hora_hasta_solicitada")));
//							r.setFecha_hora_entregado(formatter.parse(rs.getString("fecha_hora_entregado")));
//							r.setDetalle(rs.getString("detalle"));
//				
//							res.add(r);
//						}
//					}
//		} catch (SQLException sqlex) {
//			throw new AppDataException(sqlex, "Error al recuperar todas las reservas");
//		}
//		    finally{
//			try {
//				if(rs!=null) rs.close();
//				if(stmt!=null) stmt.close();
//				FactoryConexion.getInstancia().releaseConn();
//			} catch (SQLException sqlex) {
//				throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement");
//			}
//		}
//		return res;
//	}
	
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
			throw new AppDataException(sqlex,"Error al buscar el Id mas grande entre las reservas\n"+sqlex.getMessage());
		}
		finally{
			try{
			if(stmt!=null){stmt.close();}
			if(res!=null){stmt.close();}
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,sqlex.getMessage());
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
					+ "select count(*) from reserva "
					+ "where id_elemento=? and "
					+ "((?>=fecha_hora_desde_solicitada and fecha_hora_hasta_solicitada>?) or "
					+ "(?>fecha_hora_desde_solicitada and fecha_hora_hasta_solicitada>=?));");
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
			throw new AppDataException(sqlex,"Error al verificar si se puede reservar en el intervalo fechaDesde-fechaHasta");
		}
		finally{
			try{
			if(pstmt!=null){pstmt.close();}
			if(res!=null){res.close();}
			FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion,resultset o preparedstatement");
			}
		}
		return intersecciones;
	}
	
	public int numResPen(Persona persona,Reserva reserva)throws Exception{
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
			throw new AppDataException(sqlex,"Error al contar numero de reservas pendientes");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar PreparedStatement,ResultSet o Conexion");
			}
		}
		return cantidad;
	}
	
	

	public ArrayList<Reserva> getAll(Persona persona)throws Exception,SQLException,AppDataException{
		
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Reserva> reservas=new ArrayList<Reserva>();
		try{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select * "
								+ "from reserva r "
								+ "inner join elemento e   on e.id_elemento=r.id_elemento "
								+ "inner join persona p    on p.id_persona=r.id_persona "
								+ "where r.id_persona=? "
								+ "order by r.fecha_hora_reserva_hecha desc ");
								pstmt.setInt(1, persona.getId());	
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new DataElemento().getOne(res.getInt("r.id_elemento"));
					Persona per=new DataPersona().getOne(res.getInt("r.id_persona"));
					Reserva re=new Reserva();
					re=new Reserva();
					re.setId_reserva(res.getInt("r.id_reserva"));
					re.setPersona(per);
					re.setElemento(ele);
					
					re.setFecha_hora_reserva_hecha(res.getTimestamp("r.fecha_hora_reserva_hecha"));
					re.setFecha_hora_desde_solicitada(res.getTimestamp("r.fecha_hora_desde_solicitada"));
					re.setFecha_hora_hasta_solicitada(res.getTimestamp("r.fecha_hora_hasta_solicitada"));
					re.setFecha_hora_entregado(res.getTimestamp("r.fecha_hora_entregado"));
					re.setDetalle(res.getString("r.detalle"));
					
					reservas.add(re);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer las reservas del cliente\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar Conexion,ResultSet o PreparedStatement");
			}
		}
		return reservas;
	}

   
}
