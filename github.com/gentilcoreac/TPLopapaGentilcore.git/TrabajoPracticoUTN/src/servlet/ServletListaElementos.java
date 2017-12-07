package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import business.entities.Elemento;
import business.entities.TipoDeElemento;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlReservaLogic;
import business.logic.CtrlTipoDeElementoLogic;
import tools.Campo;



@WebServlet({"/ServletListaElementos","/servletlistaelementos","/SERVLETLISTAELEMENTOS"})
public class ServletListaElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
   
    public ServletListaElementos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlElementoLogic ctrl=new CtrlElementoLogic();
		try{
			if(request.getParameter("accion")!=null && request.getParameter("accion").equals("consulta")){
				Elemento ele=mapearDatos(request, response);
				Date fecha=request.getParameter("bdtportipoyfh")==null?null:Campo.parseaFecha(request.getParameter("bdtportipoyfh"));
				request.setAttribute("listaElementos", ctrl.getSome(Campo.TipoBusquedaE.valueOf(request.getParameter("selbusqueda")), ele,fecha ));
			}
			else{
				
				request.setAttribute("listaElementos", ctrl.getSome(Campo.TipoBusquedaE.TRAER_TODOS, null, null));
			}
			request.setAttribute("tiposelementos", new CtrlTipoDeElementoLogic().getAll());
			this.setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}
	
	private Elemento mapearDatos(HttpServletRequest request,HttpServletResponse response){
	    Elemento ele=new Elemento();
		switch(Campo.TipoBusquedaE.valueOf(request.getParameter("selbusqueda"))){
		case POR_ID:
					  if(Campo.Valida(request.getParameter("bporid"), Campo.tipo.ID)){
						 ele.setId_elemento(Integer.parseInt(request.getParameter("bporid")));
			          }
					  else{
						hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());	
					  }
			          break;
		case POR_NOMBRE:
					  ele.setNombre(request.getParameter("bpornombre"));		          
					  break;
		case POR_TIPO:
				      TipoDeElemento te=new TipoDeElemento();
					  te.setNombre(request.getParameter("bportipo"));
					  try {
						ele.setTipo(new CtrlTipoDeElementoLogic().getByName(te));
					  } catch (Exception ex) {
							error(request,response,ex);
					  }   	
					  break;
		case POR_NOMBRE_Y_TIPO:
					  ele.setNombre(request.getParameter("bpornombreytipo"));	
					  TipoDeElemento tde=new TipoDeElemento();
					  tde.setNombre(request.getParameter("bspornombreytipo"));
					  try {
					  	ele.setTipo(new CtrlTipoDeElementoLogic().getByName(tde));
					  } catch (Exception e) {
						error(request,response,e);
					  }				    
				      break;
		case POR_TIPO_Y_FH:
					  if(Campo.Valida(request.getParameter("bdtportipoyfh"), Campo.tipo.FECHAHORA)	){
						  try{
							  if(new CtrlReservaLogic().noEsFechaPasada(Campo.parseaFecha(request.getParameter("bdtportipoyfh")))){
								  TipoDeElemento tdel=new TipoDeElemento();
								  tdel.setNombre(request.getParameter("bsportipoyfh"));
								  ele.setTipo(new CtrlTipoDeElementoLogic().getByName(tdel));
							  }
							  else{
								  hacerInforme(request, response, TipoInforme.INFO , "Elemento", "La fecha-hora debe ser actual o futura\n"
									  		+ "No puede reservar elementos con fecha pasada");	
							
							  }
						  }
						  catch(Exception ex){
							  error(request,response,ex);
						  }
					  }
					  else{
							hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());	
					  }
					  break;
		case TRAER_TODOS:
		default:
					 ele=null;
					 break;
		}
		return ele;
	}
	
	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("bporid", request.getParameter("bporid")==null?"":request.getParameter("bporid"));
		request.setAttribute("bpornombre", request.getParameter("bpornombre")==null?"":request.getParameter("bpornombre"));
		request.setAttribute("bportipo", request.getParameter("bportipo")==null?"":request.getParameter("bportipo"));
		request.setAttribute("bpornombreytipo", request.getParameter("bpornombreytipo")==null?"":request.getParameter("bpornombreytipo"));
		request.setAttribute("bspornombreytipo", request.getParameter("bspornombreytipo")==null?"":request.getParameter("bspornombreytipo"));
		request.setAttribute("bdtportipoyfh", request.getParameter("bdtportipoyfh")==null?null:request.getParameter("bdtportipoyfh"));
		request.setAttribute("bsportipoyfh", request.getParameter("bsportipoyfh")==null?"":request.getParameter("bsportipoyfh"));
		request.setAttribute("selbusqueda", request.getParameter("selbusqueda")==null?"ocultar":request.getParameter("selbusqueda"));
	}
	
}
