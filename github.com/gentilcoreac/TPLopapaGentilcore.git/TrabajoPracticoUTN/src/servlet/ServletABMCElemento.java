package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Elemento;
import business.entities.Persona;
import business.entities.TipoDeElemento;
import business.logic.CtrlCategoriaLogic;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlPersonaLogic;
import business.logic.CtrlTipoDeElementoLogic;
import servlet.HttpServletConFunciones.TipoInforme;
import tools.Campo;


@WebServlet({"/ServletABMCElemento","/SERVLETABMCELEMENTO","/servletabmcelemento"})
public class ServletABMCElemento extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       

    public ServletABMCElemento() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("tipos", new CtrlTipoDeElementoLogic().getAll());
		} catch (Exception e) {
			error(request,response,e);
		}
		switch (request.getParameter("accion")) {
		case "alta":
			this.alta(request,response);
			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "modificacion":
			this.modificacion(request,response);
			break;
			
		case "consulta":
			this.consulta(request,response);
			break;

		default:
			this.error(request,response);
			break;
		}
	}
	
	private void consulta(HttpServletRequest request, HttpServletResponse response) {
		
		if(Campo.Valida(request.getParameter("id"), Campo.tipo.ID)){
			try {
				Elemento ele=new CtrlElementoLogic().getOne(Integer.parseInt(request.getParameter("id")));
				if(ele==null){
					hacerInforme(request, response, TipoInforme.INFO , "Elemento", "No existe ningun elemento con el id "+request.getParameter("id"));			
	
				}
				else{
					request.setAttribute("id", String.valueOf(ele.getId_elemento()));
					request.setAttribute("nombre", ele.getNombre());
					request.setAttribute("tipoelemento", ele.getTipo().getNombre());
					request.getRequestDispatcher("WEB-INF/FormElemento.jsp?accion="+request.getParameter("fin")).forward(request, response);
				}
				
				
			} catch (Exception ex) {
			
				this.error(request, response, ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());			
		}
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		
		if(Campo.Valida(request.getParameter("id"), Campo.tipo.ID)){
			try{
				CtrlElementoLogic ctrl =new CtrlElementoLogic();
				Elemento ele=ctrl.getOne(Integer.parseInt(request.getParameter("id")));
				if(ele==null){
					hacerInforme(request, response, TipoInforme.INFO , "Elemento", "No existe ningun elemento con el id "+request.getParameter("id"));			
	
				}
				else{
					ctrl.delete(ele);
					hacerInforme(request, response, TipoInforme.EXITO , "Elemento", "Elemento eliminado correctamente","ServletListaElementos");			
				}
			}
			catch(Exception ex){
				this.error(request, response,ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());			
		}
	}


	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		
			if(Campo.Valida(request.getParameter("id"), Campo.tipo.ID)){
				CtrlElementoLogic ctrl =new CtrlElementoLogic();
				Elemento ele=ctrl.getOne(Integer.parseInt(request.getParameter("id")));
				if(ele==null){
					hacerInforme(request, response, TipoInforme.INFO , "Elemento", "No existe ningun elemento con el id "+request.getParameter("id"));			
	
				}
				else{
					ele.setNombre(request.getParameter("nombre"));
					ele.setTipo(new CtrlTipoDeElementoLogic().getByName(request.getParameter("tipo")));
					ctrl.update(ele);
					hacerInforme(request, response, TipoInforme.EXITO , "Elemento", "Datos de elemento actualizados correctamente","ServletListaElementos");			
				}
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
	}


	private void alta(HttpServletRequest request, HttpServletResponse response) {
		
		try {	
				Elemento ele=new Elemento();
				ele.setNombre(request.getParameter("nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setNombre(request.getParameter("tipo"));
				ele.setTipo(new CtrlTipoDeElementoLogic().getByName(te));
				CtrlElementoLogic ctrl= new CtrlElementoLogic();
				ctrl.add(ele);
				hacerInforme(request, response, TipoInforme.EXITO , "Elemento", "Elemento agregado correctamente","ServletListaElementos");			
			
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}
	


}
