package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.entities.Persona;
import business.entities.Reserva;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlPersonaLogic;
import business.logic.CtrlReservaLogic;
import tools.Campo;


@WebServlet({"/ServletListaReservas","/SERVLETLISTARESERVAS","/servletlistareservas"})
public class ServletListaReservas extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
    
    public ServletListaReservas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try{
			CtrlReservaLogic ctrl=new CtrlReservaLogic();
			Campo.TipoBusquedaR tbr;
			if(request.getParameter("accion")!=null && request.getParameter("accion").equals("consulta")){
				tbr=Campo.TipoBusquedaR.valueOf(request.getParameter("rselbusqueda"));
			}
			else{
				tbr=Campo.TipoBusquedaR.TRAER_TODAS;
			}
			Reserva res=mapearDatos(request, response,tbr);
			if(res!=null){
				request.setAttribute("listaReservas", ctrl.getSome((Persona)request.getSession().getAttribute("user"),tbr,res));
				this.setearAtributos(request);
				request.getRequestDispatcher("WEB-INF/ListaReservas.jsp").forward(request, response);	
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Reserva", Campo.getMensaje());	
			}

		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}
	
	private Reserva mapearDatos(HttpServletRequest request,HttpServletResponse response,Campo.TipoBusquedaR tbr)throws Exception{
	    Reserva res=new Reserva();
		switch(tbr){
		case POR_IDRESERVA:
					  if(Campo.Valida(request.getParameter("brporidreserva"), Campo.tipo.ID)){
						 res.setId_reserva(Integer.parseInt(request.getParameter("brporidreserva")));
			          }
					  else{
						  return null;	
						  }				  
			          break;
		case POR_IDELEMENTO:
					  if(Campo.Valida(request.getParameter("brporidelemento"), Campo.tipo.ID)){
						 res.setElemento(new CtrlElementoLogic().getOne(Integer.parseInt(request.getParameter("brporidelemento"))));
			          }
					  else{
						  return null;
					  }					  
			          break;
		case POR_IDPERSONA:
					  if(Campo.Valida(request.getParameter("brporidpersona"), Campo.tipo.ID)){
						 res.setPersona(new CtrlPersonaLogic().getOne(Integer.parseInt(request.getParameter("brporidpersona"))));
			          }
					  else{
						 return null;
					  }
			          break;
		case PENDIENTES:
		case VENCIDAS:
		case TRAER_TODAS:
		default:
					 break;
		}
		return res;
	}
	
	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("brporidreserva", request.getParameter("brporidreserva")==null?"":request.getParameter("brporidreserva"));
		request.setAttribute("brporidelemento", request.getParameter("brporidelemento")==null?"":request.getParameter("brporidelemento"));
		request.setAttribute("brporidpersona", request.getParameter("brporidpersona")==null?"":request.getParameter("brporidpersona"));
		request.setAttribute("besportipoyfh", request.getParameter("besportipoyfh")==null?"":request.getParameter("besportipoyfh"));
		request.setAttribute("rselbusqueda", request.getParameter("rselbusqueda")==null?"ocultar":request.getParameter("rselbusqueda"));
	}
}	
