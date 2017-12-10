package business.logic;

import java.util.ArrayList;


import business.entities.TipoDeElemento;
import data.DataTipoDeElemento;

public class CtrlTipoDeElementoLogic {
	private DataTipoDeElemento dataTdE;
	private ArrayList<TipoDeElemento> tdes; 
	
	public CtrlTipoDeElementoLogic(){
		dataTdE = new DataTipoDeElemento();
		tdes = new ArrayList<TipoDeElemento>();
	}
	
	public TipoDeElemento getOne(int id_elemento)throws Exception{
		TipoDeElemento tde = new TipoDeElemento();
		tde.setId(id_elemento);
		return getOne(tde);
	}
	
	public TipoDeElemento getOne(TipoDeElemento tde) throws Exception{
		return this.dataTdE.getOne(tde);
	}
	
	public TipoDeElemento getByName(TipoDeElemento tde) throws Exception{
		return this.dataTdE.getByName(tde);
	}
	public TipoDeElemento getByName(String nombre) throws Exception{
		TipoDeElemento te=new TipoDeElemento();
		te.setNombre(nombre);
		return this.dataTdE.getByName(te);
	}
	
	public ArrayList<TipoDeElemento> getAll() throws Exception{
		return dataTdE.getAll();
	}	
	
	public void add(TipoDeElemento tde) throws Exception{
		dataTdE.add(tde);
	}	
	public void update(TipoDeElemento tde) throws Exception{
		dataTdE.update(tde);
	}
	public void delete(TipoDeElemento tde) throws Exception{
		dataTdE.delete(tde);
	}
	

	
}
