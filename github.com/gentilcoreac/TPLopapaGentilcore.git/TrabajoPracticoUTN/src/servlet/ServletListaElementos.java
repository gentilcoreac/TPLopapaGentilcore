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
		
		try{
			CtrlElementoLogic ctrl=new CtrlElementoLogic();
			Campo.TipoBusquedaE tbe;
			if(request.getParameter("accion")!=null && request.getParameter("accion").equals("consulta")){
				tbe=Campo.TipoBusquedaE.valueOf(request.getParameter("eselbusqueda"));
			}
			else{
				tbe=Campo.TipoBusquedaE.TRAER_TODOS;
			}
			Elemento ele=mapearDatos(request, response,tbe);
			if(ele!=null){
				Date fecha=request.getParameter("bedtportipoyfh")==null?null:Campo.parseaFecha(request.getParameter("bedtportipoyfh"));
				request.setAttribute("listaElementos", ctrl.getSome(tbe, ele,fecha ));
				request.setAttribute("tiposelementos", new CtrlTipoDeElementoLogic().getAll());
				this.setearAtributos(request);
				request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());	
			}
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}
	
	private Elemento mapearDatos(HttpServletRequest request,HttpServletResponse response,Campo.TipoBusquedaE tbe)throws Exception{
	    Elemento ele=new Elemento();
		switch(tbe){
		case POR_ID:
					  if(Campo.Valida(request.getParameter("beporid"), Campo.tipo.ID)){
						 ele.setId_elemento(Integer.parseInt(request.getParameter("beporid")));
			          }
					  else{
						 return null;
					  }
			          break;
		case POR_NOMBRE:
					  ele.setNombre(request.getParameter("bepornombre"));		          
					  break;
		case POR_TIPO:
				      TipoDeElemento te=new TipoDeElemento();
					  te.setNombre(request.getParameter("beportipo"));
					  ele.setTipo(new CtrlTipoDeElementoLogic().getByName(te));	
					  break;
		case POR_NOMBRE_Y_TIPO:
					  ele.setNombre(request.getParameter("bepornombreytipo"));	
					  TipoDeElemento tde=new TipoDeElemento();
					  tde.setNombre(request.getParameter("bespornombreytipo"));
					  ele.setTipo(new CtrlTipoDeElementoLogic().getByName(tde));
				      break;
		case POR_TIPO_Y_FH:
					  if(Campo.Valida(request.getParameter("bedtportipoyfh"), Campo.tipo.FECHAHORA)	){
					
						  if(new CtrlReservaLogic().noEsFechaPasada(Campo.parseaFecha(request.getParameter("bedtportipoyfh")))){
							  TipoDeElemento tdel=new TipoDeElemento();
							  tdel.setNombre(request.getParameter("besportipoyfh"));
							  ele.setTipo(new CtrlTipoDeElementoLogic().getByName(tdel));
						  }
						  else{
							  Campo.setMensaje("La fecha-hora debe ser actual o futura\n"
								  		+ "No puede reservar elementos con fecha pasada");
							  return null;	
						
						  }
					  }
					  else{
							return null;	
					  }
					  break;
		case TRAER_TODOS:
		default:
					 break;
		}
		return ele;
	}
	
	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("beporid", request.getParameter("beporid")==null?"":request.getParameter("beporid"));
		request.setAttribute("bepornombre", request.getParameter("bepornombre")==null?"":request.getParameter("bepornombre"));
		request.setAttribute("beportipo", request.getParameter("beportipo")==null?"":request.getParameter("beportipo"));
		request.setAttribute("bepornombreytipo", request.getParameter("bepornombreytipo")==null?"":request.getParameter("bepornombreytipo"));
		request.setAttribute("bespornombreytipo", request.getParameter("bespornombreytipo")==null?"":request.getParameter("bespornombreytipo"));
		request.setAttribute("bedtportipoyfh", request.getParameter("bedtportipoyfh")==null?null:request.getParameter("bedtportipoyfh"));
		request.setAttribute("besportipoyfh", request.getParameter("besportipoyfh")==null?"":request.getParameter("besportipoyfh"));
		request.setAttribute("eselbusqueda", request.getParameter("eselbusqueda")==null?"ocultar":request.getParameter("eselbusqueda"));
	}
	
}
