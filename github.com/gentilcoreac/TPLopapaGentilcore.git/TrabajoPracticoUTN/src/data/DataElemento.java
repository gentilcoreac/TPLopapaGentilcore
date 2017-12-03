package data;

import java.util.ArrayList;
import java.sql.*;
import business.entities.Elemento;
import business.entities.TipoDeElemento;
import tools.AppDataException;
import tools.Campo;


public class DataElemento {
	//id_elemento, nombre, tipo
	

	public ArrayList<Elemento> getAll() throws SQLException, AppDataException{
		Statement stmt = null;
		ResultSet rs=null;
		ArrayList<Elemento> elems= new ArrayList<Elemento>(); 
		DataTipoDeElemento dtde = new DataTipoDeElemento();	
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select* from elemento");
			if(rs!=null){
				while(rs.next()){
					Elemento el = new Elemento();
					el.setNombre(rs.getString("nombre"));
					el.setId_elemento(rs.getInt("id_elemento"));
					int idTipEl = rs.getInt("id_tipodeelemento");
					el.setTipo(dtde.getOne(idTipEl));
					elems.add(el);				
				}
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al recuperar todos los elementos");
		}
		finally{
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					FactoryConexion.getInstancia().releaseConn();
				} catch (SQLException sqlex) {
					throw new AppDataException(sqlex, "Error al cerrar conexion, resultset o statement (Clase: DataElemento)");
				}		
		}
		return elems;	
	}
	
	/*
	public ArrayList<Elemento> getSome(Campo.TipoBusquedaE tipob,Elemento elemento,int indice,int cantTraer)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
			
			switch(tipob){
			case POR_ID:
						pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento e "
						//+"left join reserva r on e.id_elemento=r.id_elemento "
						+ "inner join tipodeelemento te "
						+ "on e.id_tipodeelemento=te.id_tipodeelemento "
						+ "where e.id_elemento=? "
						+ "limit ?,?");
						pstmt.setInt(1, elemento.getId_elemento());
						pstmt.setInt(2, indice);
						pstmt.setInt(3, cantTraer);
						break;
			case POR_NOMBRE:
							String nombre=elemento.getNombre();
							if(nombre==null || nombre.isEmpty()){
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										//+"left join reserva r on e.id_elemento=r.id_elemento "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where e.nombre is null || e.nombre='' "
										+ "limit ?,?");
								pstmt.setInt(1, indice);
								pstmt.setInt(2, cantTraer);
							}else{
			
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select* from elemento e "
										//+"left join reserva r on e.id_elemento=r.id_elemento "
										+ "inner join tipodeelemento te "
										+ "on e.id_tipodeelemento=te.id_tipodeelemento "
										+ "where e.nombre like ? "
										+ "limit ?,?");
								pstmt.setString(1, nombre+"%");
								pstmt.setInt(2, indice);
								pstmt.setInt(3, cantTraer);
							}
							break;
			case POR_TIPO:
							int idTipo=elemento.getTipo().getId();
							pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
									+ "select* from elemento e "
									//+"left join reserva r on e.id_elemento=r.id_elemento "
									+ "inner join tipodeelemento te "
									+ "on e.id_tipodeelemento=te.id_tipodeelemento "
									+ "where e.id_tipodeelemento=? "
									+ "limit ?,?");
							pstmt.setInt(1, idTipo);
							pstmt.setInt(2, indice);
							pstmt.setInt(3, cantTraer);
							break;
			case POR_NOMBRE_Y_TIPO:
				int idTipoele=elemento.getTipo().getId();
				String nom=elemento.getNombre();
				if(nom==null || nom.isEmpty()){
					pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
							+ "select* from elemento e "
							//+"left join reserva r on e.id_elemento=r.id_elemento "
							+ "inner join tipodeelemento te "
							+ "on e.id_tipodeelemento=te.id_tipodeelemento "
							+ "where (e.nombre is null || e.nombre='') and e.id_tipodeelemento=? "
							+ "limit ?,?");
					pstmt.setInt(1, idTipoele);
					pstmt.setInt(2, indice);
					pstmt.setInt(3, cantTraer);
				}
				else{
					pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
							+ "select* from elemento e "
							//+"left join reserva r on e.id_elemento=r.id_elemento "
							+ "inner join tipodeelemento te "
							+ "on e.id_tipodeelemento=te.id_tipodeelemento "
							+ "where e.nombre like ? and e.id_tipodeelemento=? "
							+ "limit ?,?");
				pstmt.setString(1, nom+"%");
				pstmt.setInt(2, idTipoele);
				pstmt.setInt(3, indice);
				pstmt.setInt(4, cantTraer);
				}
									break;
			case TRAER_TODOS:
			default:
					pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento e "
						//+"left join reserva r on e.id_elemento=r.id_elemento "
						+ "inner join tipodeelemento te "
						+ "on e.id_tipodeelemento=te.id_tipodeelemento "
						+ "limit ?,?");
					pstmt.setInt(1, indice);
					pstmt.setInt(2, cantTraer);break;
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
					ele.setTipo(te);
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos\n"+sqlex.getMessage());
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
		return elementos;
	}*/
	
	public ArrayList<Elemento> getSome(Elemento elemento,java.util.Date fechaDisp,int indice,int cantTraer)throws SQLException,AppDataException,Exception{
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
					+ "where te.nombre=? and  "
					+ "(res.fecha_hora_desde_solicitada is null or  "
					+ "	( "
					+ "	(res.fecha_hora_desde_solicitada >? and res.fecha_hora_hasta_solicitada>?) "
					+ "		or "
					+ "	(res.fecha_hora_desde_solicitada<? and res.fecha_hora_hasta_solicitada<?) "
					+ "	) "
					+ ") "
					+ "limit ?,?");
			pstmt.setString(1, elemento.getTipo().getNombre());
			pstmt.setString(2, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(4, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(5, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setInt(6, indice);
			pstmt.setInt(7, cantTraer);
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
					ele.setTipo(te);
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer elementos de un tipo disponibles en una fecha");
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
		return elementos;
	}
	
	public ArrayList<Elemento> getSome(int indice,int cantTraer)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "limit ?,?");
			
			pstmt.setInt(1, indice);
			pstmt.setInt(2, cantTraer);
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("id_elemento"));
					ele.setNombre(res.getString("nombre"));
					ele.setTipo(new DataTipoDeElemento().getOne(res.getInt("id_tipodeelemento")));
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos");
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
		return elementos;
	}
	
	public ArrayList<Elemento> getSome(String nombre,int indice,int cantTraer)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
			if(nombre==null || nombre.isEmpty()){
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "where nombre is null "
						+ "limit ?,?");
				pstmt.setInt(1, indice);
				pstmt.setInt(2, cantTraer);
			}else{

				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "where nombre like ? "
						+ "limit ?,?");
				pstmt.setString(1, nombre+"%");
				pstmt.setInt(2, indice);
				pstmt.setInt(3, cantTraer);
			}
			
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("id_elemento"));
					ele.setNombre(res.getString("nombre"));
					ele.setTipo(new DataTipoDeElemento().getOne(res.getInt("id_tipodeelemento")));
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos por nombre");
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
		return elementos;
	}

	public ArrayList<Elemento> getSome(int idTipo,int indice,int cantTraer)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "where id_tipodeelemento=? "
						+ "limit ?,?");
			pstmt.setInt(1, idTipo);
			pstmt.setInt(2, indice);
			pstmt.setInt(3, cantTraer);
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("id_elemento"));
					ele.setNombre(res.getString("nombre"));
					ele.setTipo(new DataTipoDeElemento().getOne(res.getInt("id_tipodeelemento")));
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos por Tipo");
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
		return elementos;
	}
	
	public ArrayList<Elemento> getSome(String nombre,int idTipo,int indice,int cantTraer)throws SQLException,AppDataException{
		PreparedStatement pstmt=null;
		ResultSet res=null;
		ArrayList<Elemento> elementos=new ArrayList<Elemento>();
		try{
			if(nombre==null || nombre.isEmpty()){
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "where nombre is null and id_tipodeelemento=? "
						+ "limit ?,?");
				pstmt.setInt(1, idTipo);
				pstmt.setInt(2, indice);
				pstmt.setInt(3, cantTraer);
			}
			else{
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select* from elemento "
						+ "where nombre like ? and id_tipodeelemento=? "
						+ "limit ?,?");
			pstmt.setString(1, nombre+"%");
			pstmt.setInt(2, idTipo);
			pstmt.setInt(3, indice);
			pstmt.setInt(4, cantTraer);
			}
				
			res=pstmt.executeQuery();
			if(res!=null){
				while(res.next()){
					Elemento ele=new Elemento();
					ele.setId_elemento(res.getInt("id_elemento"));
					ele.setNombre(res.getString("nombre"));
					ele.setTipo(new DataTipoDeElemento().getOne(res.getInt("id_tipodeelemento")));
					elementos.add(ele);
				}
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al traer un grupo de elementos por Nombre y Tipo");
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
		return elementos;
	}
	
	
	/*
	public int getCantidad(Campo.TipoBusquedaE tipob,Elemento ele)throws SQLException,AppDataException{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			switch(tipob){
			case POR_ID:
						pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
								+ "select count(*) from elemento where id_elemento=?;");
						pstmt.setInt(1, ele.getId_elemento());
						break;
			case POR_NOMBRE:
							String nombre=ele.getNombre();
							if(nombre==null || nombre.isEmpty()){
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
									+ "select count(*) from elemento where nombre is null;");}
							else{
								pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
										+ "select count(*) from elemento where nombre like ?");
								pstmt.setString(1, nombre+"%");
							}
							break;
			case POR_TIPO:
							pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
									+ "select count(*) from elemento where id_tipodeelemento=?;");
							pstmt.setInt(1, ele.getTipo().getId());
							break;
			case POR_NOMBRE_Y_TIPO:
									String elnom=ele.getNombre();
									int idtip=ele.getTipo().getId();
									if(elnom==null || elnom.isEmpty()){
										pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
											+ "select count(*) from elemento where nombre is null and id_tipodeelemento=?;");
										pstmt.setInt(1, idtip);}
									else{
										pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
												+ "select count(*) from elemento where nombre like ? and id_tipodeelemento=?;");
										pstmt.setString(1, elnom+"%");
										pstmt.setInt(2, idtip);
									}
									break;
			case TRAER_TODOS:
			default:
					pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select count(*) from elemento;");
					break;
			}
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos\n"+sqlex.getMessage());
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,Statement o Resultset");
			}
		}
		return cantidad;
	}*/
	
	public int getCantidad(Elemento elemento,java.util.Date fechaDisp)throws SQLException,AppDataException{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) "
					+ "from elemento ele "
					+ "left join reserva res "
					+ "on ele.id_elemento=res.id_elemento "
					+ "inner join tipodeelemento te "
					+ "on te.id_tipodeelemento=ele.id_tipodeelemento "
					+ "where te.nombre=? and  "
					+ "(res.fecha_hora_desde_solicitada is null or  "
					+ "	( "
					+ "	(res.fecha_hora_desde_solicitada >? and res.fecha_hora_hasta_solicitada>?) "
					+ "		or "
					+ "	(res.fecha_hora_desde_solicitada<? and res.fecha_hora_hasta_solicitada<?) "
					+ "	) "
					+ ");");
			pstmt.setString(1, elemento.getTipo().getNombre());
			pstmt.setString(2, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(3, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(4, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			pstmt.setString(5, new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(fechaDisp));
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos buscados por tipo y fecha hora");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,PreparedStatement o Resultset");
			}
		}
		return cantidad;
	}
	
	public int getCantidad()throws SQLException,AppDataException{
		int cantidad=0;
		Statement stmt=null;
		ResultSet res=null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().createStatement();
			res=stmt.executeQuery("select count(*) from elemento;");
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos");
		}
		finally{
			try{
				if(stmt!=null){stmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,Statement o Resultset");
			}
		}
		return cantidad;
	}
	
	public int getCantidad(int idTipo)throws SQLException,AppDataException{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) from elemento where id_tipodeelemento=?;");
			pstmt.setInt(1, idTipo);
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos buscados por tipo");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,Statement o Resultset");
			}
		}
		return cantidad;
	}
	
	public int getCantidad(String nombre)throws SQLException,AppDataException{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			if(nombre==null || nombre.isEmpty()){
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) from elemento where nombre is null;");}
			else{
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select count(*) from elemento where nombre like ?");
				pstmt.setString(1, nombre+"%");
			}
			
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos buscados por nombre");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,Statement o Resultset");
			}
		}
		return cantidad;
	}
	
	public int getCantidad(String nombre,int idTipo)throws SQLException,AppDataException{
		int cantidad=0;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		try{
			if(nombre==null || nombre.isEmpty()){
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
					+ "select count(*) from elemento where nombre is null and id_tipodeelemento=?;");
				pstmt.setInt(1, idTipo);}
			else{
				pstmt=FactoryConexion.getInstancia().getConn().prepareStatement(""
						+ "select count(*) from elemento where nombre like ? and id_tipodeelemento=?;");
				pstmt.setString(1, nombre+"%");
				pstmt.setInt(2, idTipo);
			}
			
			res=pstmt.executeQuery();
			if(res!=null && res.next()){
				cantidad=res.getInt(1);
			}
		}
		catch(SQLException sqlex){
			throw new AppDataException(sqlex,"Error al contar elementos buscados por nombre");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(res!=null){res.close();}
				FactoryConexion.getInstancia().releaseConn();}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerra Conexion,Statement o Resultset");
			}
		}
		return cantidad;
	}

	public Elemento getOne(Elemento elem) throws Exception,SQLException, AppDataException{
		Elemento e =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataTipoDeElemento dtde = new DataTipoDeElemento();
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_elemento,nombre,id_tipodeelemento from elemento where id_elemento=?"
			/*//		
			 "select e.id_elemento, e.nombre_elemento, e.id_tipodeelemento"
					+ " from elemento e "
					+ " inner join tipodeelemento tde "
					+ "  on e.id_tipo=tde.id_tipodeelemento "
					+ " where e.id_elemento=? "*/
					);
			pstmt.setInt(1,elem.getId_elemento());
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				e = new Elemento();
				e.setId_elemento(rs.getInt("id_elemento"));
				e.setNombre(rs.getString("nombre"));

				//e.getTipo().setId(rs.getInt("id_tipo"));
				//e.getTipo().setNombre(rs.getString("nombre_tipo_elemento"));
				
				int idTipo = rs.getInt("id_tipodeelemento");
				e.setTipo(dtde.getOne(idTipo));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una Elemento");
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion en busqueda de elemento");
			}
		}
		
		return e;
	}
	
	
	public Elemento getOne(int id_elem_p) throws SQLException, AppDataException{
		Elemento e =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataTipoDeElemento dtde = new DataTipoDeElemento();
		
		try {
			pstmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from elemento where id_elemento=?"
			/*		"select e.id_elemento, e.nombre_elemento, e.id_tipo"
					+ " from elemento e "
					+ " inner join tipodeelemento tde "
					+ "  on e.id_tipo=tde.id_tipodeelemento "
					+ " where e.id_elemento=? "*/
					);
			pstmt.setInt(1,id_elem_p);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				e = new Elemento();
				e.setId_elemento(rs.getInt("id_elemento"));
				e.setNombre(rs.getString("nombre"));

				//e.getTipo().setId(rs.getInt("id_tipo"));
				//e.getTipo().setNombre(rs.getString("nombre_tipo_elemento"));
				
				int idTipo = rs.getInt("id_tipodeelemento");
				e.setTipo(dtde.getOne(idTipo));
			}
		} catch (SQLException sqlex) {
			throw new AppDataException(sqlex, "Error al buscar una Elemento");
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException sqlex) {
				throw new AppDataException(sqlex, "Error al cerrar conexion en busqueda de elemento");
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
			throw new AppDataException(sqlex,"Error al agregar elemento");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(KeyRes!=null){KeyRes.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion, resultset o statement");
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
			throw new AppDataException(sqlex,"Error al borrar elemento");
		}
		finally{
			try{
				if(pstmt1!=null){pstmt1.close();}
				if(pstmt2!=null){pstmt2.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement");
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
			throw new AppDataException(sqlex,"Error al modificar elemento");
		}
		finally{
			try{
				if(pstmt!=null){pstmt.close();}
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException sqlex){
				throw new AppDataException(sqlex,"Error al cerrar conexion o PreparedStatement");}
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
			throw new AppDataException(sqlex,"Error al buscar el Id mas grande entre los elementos\n"+sqlex.getMessage());
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
}
