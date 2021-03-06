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
		request.setAttribute("idelelistado", request.getParameter("id")==null?"":request.getParameter("id"));
		request.setAttribute("nombreelelistado", request.getParameter("nombre")==null?"":request.getParameter("nombre"));
		request.setAttribute("tipoelelistado", request.getParameter("tipoelemento")==null?"":request.getParameter("tipoelemento"));
		request.setAttribute("nombreelelistadores", request.getParameter("nombreelelistadores")==null?"":request.getParameter("nombreelelistadores"));
		request.setAttribute("tipoelelistadores", request.getParameter("tipoelelistadores")==null?"":request.getParameter("tipoelelistadores"));
		request.setAttribute("limite_horas_res", request.getParameter("limite_horas_res")==null?"":request.getParameter("limite_horas_res"));
		request.setAttribute("dias_max_anticipacion", request.getParameter("dias_max_anticipacion")==null?"":request.getParameter("dias_max_anticipacion"));
		request.setAttribute("nombreusulistadores", request.getParameter("nombreusulistadores")==null?"":request.getParameter("nombreusulistadores"));
		request.setAttribute("apellidousulistadores", request.getParameter("apellidousulistadores")==null?"":request.getParameter("apellidousulistadores"));
		request.setAttribute("dniusulistadores", request.getParameter("dniusulistadores")==null?"":request.getParameter("dniusulistadores"));
	}
		
	


}
