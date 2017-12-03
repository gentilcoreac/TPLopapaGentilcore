package business.logic;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import business.entities.Elemento;
import business.entities.TipoDeElemento;
import data.DataElemento;
import data.DataTipoDeElemento;
import tools.Campo;


public class CtrlElementoLogic {
	private DataElemento dataElem;
	private DataTipoDeElemento dataTipoElem;
	public ArrayList<Elemento> elementos;	
	
	public CtrlElementoLogic(){
		dataElem = new DataElemento();		
		dataTipoElem = new DataTipoDeElemento();
		elementos=new ArrayList<Elemento>();
	}
	
	public ArrayList<Elemento> getAll()throws Exception{
		return dataElem.getAll();
	}
	
	public int getMaxId()throws Exception{
		return dataElem.getMaxId();
	}
/*	public ArrayList<Elemento> getSome(Campo.TipoBusquedaE tipob,Elemento ele,Date fechaDisp,int indice,int cantidad)throws Exception{

		if(tipob==Campo.TipoBusquedaE.POR_TIPO_Y_FH){
			return dataElem.getSome(ele, fechaDisp, indice, cantidad);
		}
		return dataElem.getSome(tipob,ele,indice,cantidad);
		
	}*/
	
/*	public int getCantidad(Campo.TipoBusquedaE tipob,Elemento ele,Date fechaDisp)throws Exception{
		
		if(tipob==Campo.TipoBusquedaE.POR_TIPO_Y_FH){
			return dataElem.getCantidad(ele,fechaDisp);
		}
		return dataElem.getCantidad(tipob,ele);
		
	}*/
	
	
	
	
	
	
	
	public int getCantidad(Elemento ele,Date fechaDisp)throws Exception{
		return dataElem.getCantidad(ele,fechaDisp);	
	}
	
	
	
	
	
	public void add(Elemento e) throws Exception{
		dataElem.add(e);
	}

	public Elemento getOne(Elemento e) throws Exception{
		return this.dataElem.getOne(e);
	}
	
	public Elemento getOne(int id_elem) throws Exception{
		Elemento e = new Elemento();
		e.setId_elemento(id_elem);
		return getOne(e);
	}
	
	public void update(Elemento e) throws Exception{
		dataElem.update(e);
	}
	
	public void delete(Elemento e) throws Exception{
		dataElem.delete(e);
	}
	
	public ArrayList<TipoDeElemento> getTipoDeElemento() throws Exception{
		return dataTipoElem.getAll();
	}
	
}