package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.logic.CtrlReservaLogic;


@WebServlet({"/ServletFormsReservas","/SERVLETFORMSRESERVAS","/servletformsreservas"})
public class ServletFormsReservas extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
   
    public ServletFormsReservas() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("accion")) {
		case "alta":
			this.alta(request, response);
			break;
			
		case "cerrar":
			this.cerrar(request, response);
			break;
		case "baja":
			this.baja(request, response);
			break;
		default:
			this.error(request,response);
			break;
		}

	}
	


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			this.setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormReserva.jsp?accion=baja").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
		
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.setearAtributos(request);
			request.setAttribute("proxid", new CtrlReservaLogic().getMaxId()+1);
			request.getRequestDispatcher("WEB-INF/FormReserva.jsp?accion=alta").forward(request, response);
		} catch (Exception  e) {
			error(request,response,e);
		}
	}
	private void cerrar(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormReserva.jsp?accion=cerrar").forward(request, response);
	
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}	

		
	}

	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("idreserva", request.getParameter("idreserva")==null?"":request.getParameter("idreserva"));
		request.setAttribute("idpersona", request.getParameter("idpersona")==null?"":request.getParameter("idpersona"));
		request.setAttribute("idelemento", request.getParameter("idelemento")==null?"":request.getParameter("idelemento"));
		request.setAttribute("fechareservahecha", request.getParameter("fechareservahecha")==null?null:request.getParameter("fechareservahecha"));
		request.setAttribute("fechareservadesde", request.getParameter("fechareservadesde")==null?null:request.getParameter("fechareservadesde"));
		request.setAttribute("fechareservahasta", request.getParameter("fechareservahasta")==null?null:request.getParameter("fechareservahasta"));
		request.setAttribute("fechareservaentrega", request.getParameter("fechareservaentrega")==null?null:request.getParameter("fechareservaentrega"));
		request.setAttribute("detalle", request.getParameter("detalle")==null?"":request.getParameter("detalle"));
		request.setAttribute("ideledesdelistado", request.getParameter("id")==null?"":request.getParameter("id"));
	}
		
	


}
