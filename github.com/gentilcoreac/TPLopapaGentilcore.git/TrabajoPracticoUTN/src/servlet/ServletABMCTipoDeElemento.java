package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import business.entities.TipoDeElemento;
import business.logic.CtrlTipoDeElementoLogic;
import tools.Campo;


@WebServlet({"/ServletABMCTipoDeElemento/*","/servletabmctipodeelemento/*","/SERVLETABMCTIPODEELEMENTO/*"})
public class ServletABMCTipoDeElemento extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
 
    public ServletABMCTipoDeElemento() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		try {
			TipoDeElemento t=new TipoDeElemento();
			t.setNombre(request.getParameter("nombre"));
			TipoDeElemento te=new CtrlTipoDeElementoLogic().getByName(t);
			if(te==null){
				hacerInforme(request, response, TipoInforme.INFO , "Tipo de Elemento", "No existe ningun tipo de elemento con el nombre "+request.getParameter("nombre"));			

			}
			else{
				request.setAttribute("id", String.valueOf(te.getId()));
				request.setAttribute("nombre", te.getNombre());
				request.setAttribute("cant_max_res_pen", String.valueOf(te.getCant_max_res_pen()));
				request.setAttribute("limite_horas_res", String.valueOf(te.getLimite_horas_res()));
				request.setAttribute("dias_max_anticipacion", String.valueOf(te.getDias_max_anticipacion()));
				request.setAttribute("only_encargados", String.valueOf(te.isOnly_encargados()));
				request.setAttribute("urlcancelar", request.getParameter("urlcancelar"));
				request.getRequestDispatcher("WEB-INF/FormTipoDeElemento.jsp?accion="+request.getParameter("fin")).forward(request, response);

			}
			
			
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		
			try{
				TipoDeElemento te=new TipoDeElemento();
				te.setNombre(request.getParameter("nombre"));
				CtrlTipoDeElementoLogic ctrl =new CtrlTipoDeElementoLogic();
				ctrl.delete(te);
				hacerInforme(request, response, TipoInforme.EXITO , "Tipo de Elemento", "Tipo de Elemento eliminado correctamente","ServletListaTiposDeElementos");			

			}
			catch(Exception ex){
				this.error(request, response,ex);
			}
		
		
	}


	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	
			if(this.validaCampos(request.getParameter("cant_max_res_pen"),request.getParameter("dias_max_anticipacion"),request.getParameter("limite_horas_res"))){
				TipoDeElemento te=new TipoDeElemento();
				te.setNombre(request.getParameter("nombre"));
				te.setCant_max_res_pen(Integer.parseInt(request.getParameter("cant_max_res_pen")));
				te.setDias_max_anticipacion(Integer.parseInt(request.getParameter("dias_max_anticipacion")));
				te.setLimite_horas_res(Integer.parseInt(request.getParameter("limite_horas_res")));
				te.setOnly_encargados(request.getParameter("only_encargados")==null?false:true);
				CtrlTipoDeElementoLogic ctrl= new CtrlTipoDeElementoLogic();
				ctrl.update(te);
				hacerInforme(request, response, TipoInforme.EXITO , "Tipo de Elemento", "Datos de Tipo de Elemento actualizados correctamente","ServletListaTiposDeElementos");			

			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Tipo de Elemento", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
	}


	private void alta(HttpServletRequest request, HttpServletResponse response) {
		
		try {

			if(this.validaCampos(request.getParameter("cant_max_res_pen"),request.getParameter("dias_max_anticipacion"),request.getParameter("limite_horas_res"))){
				TipoDeElemento te=new TipoDeElemento();
				te.setNombre(request.getParameter("nombre"));
				te.setCant_max_res_pen(Integer.parseInt(request.getParameter("cant_max_res_pen")));
				te.setDias_max_anticipacion(Integer.parseInt(request.getParameter("dias_max_anticipacion")));
				te.setLimite_horas_res(Integer.parseInt(request.getParameter("limite_horas_res")));
				te.setOnly_encargados(request.getParameter("only_encargados")==null?false:true);
				CtrlTipoDeElementoLogic ctrl= new CtrlTipoDeElementoLogic();
				ctrl.add(te);
				hacerInforme(request, response, TipoInforme.EXITO , "Tipo de Elemento", "Tipo de Elemento agregado correctamente","ServletListaTiposDeElementos");			
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Tipo de Elemento", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}
	
	private boolean validaCampos(String maxrespen,String diasant,String limhor) {

		
		return (Campo.Valida(maxrespen, Campo.tipo.MAXRESPEN) && 
		   Campo.Valida(limhor, Campo.tipo.LIMHOR)&& 
		   Campo.Valida(diasant, Campo.tipo.DIASANT));
		 

	}
	
}
