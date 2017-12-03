package business.logic;

import java.util.ArrayList;

import business.entities.Categoria;
import business.entities.Persona;
import data.DataCategoria;
import data.DataPersona;

public class CtrlPersonaLogic {
	private DataPersona dataPer;
	private DataCategoria dataCat;
	private ArrayList<Persona> pers;
	
	public CtrlPersonaLogic(){
		dataPer = new DataPersona();
		dataCat = new DataCategoria();
		pers = new ArrayList<Persona>();
	}
	
	public void add(Persona p) throws Exception{
		dataPer.add(p);
	}
	
	public Persona getByDni(Persona p) throws Exception{
		return this.dataPer.getByDni(p);
	}
	
	public Persona getOne(Persona p)throws Exception{
			return dataPer.getOne(p);
	}
	
	public Persona getOne(int id)throws Exception{
		return dataPer.getOne(id);
	}
	
	public Persona getByDni(String dni) throws Exception{
		Persona p = new Persona();
		p.setDni(dni);
		return getByDni(p);
	}
	
	public void update(Persona p) throws Exception{
		dataPer.update(p);
	}
	
	public void delete(Persona p) throws Exception{
		dataPer.delete(p);
	}
	
	public ArrayList<Persona> getAll() throws Exception{
		return dataPer.getAll();

	}
	
	public Persona getLoggedUser(String usuario,String pass)throws Exception{
		return dataPer.getLoggedUser(usuario, pass);
	}
	
/*	public Persona getLoggedUser(Persona per)throws Exception{
		return dataPer.getLoggedUser(per);
	}	*/



	
	
	public ArrayList<Categoria> getCategorias() throws Exception{
		return dataCat.getAll();
	}
	
}	


