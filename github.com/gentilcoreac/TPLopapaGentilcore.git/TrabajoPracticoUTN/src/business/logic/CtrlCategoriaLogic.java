package business.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import business.entities.Categoria;
import data.DataCategoria;
import tools.AppDataException;

public class CtrlCategoriaLogic {
	private DataCategoria dataCat;
	private ArrayList<Categoria> cats;
	
	public CtrlCategoriaLogic(){
		dataCat = new DataCategoria();
		cats = new ArrayList<Categoria>();
	}
	
	public void add(Categoria c) throws Exception{
		dataCat.add(c);
	}
	
	public Categoria getOne(Categoria c) throws Exception{
		return this.dataCat.getOne(c);
	}

	
	public Categoria getOne(int idCat) throws Exception{
		Categoria c = new Categoria();
		c.setId(idCat);
		return getOne(c);
	}
	
	public ArrayList<Categoria> getAll() throws Exception{
		return this.dataCat.getAll();
	}
}
