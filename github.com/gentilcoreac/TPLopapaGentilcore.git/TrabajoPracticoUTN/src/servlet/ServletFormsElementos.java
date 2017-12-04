package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlElementoLogic;
import business.logic.CtrlTipoDeElementoLogic;


@WebServlet({"/ServletFormsElementos","/SERVLETFORMSELEMENTOS","/servletformselementos"})
public class ServletFormsElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
    
    public ServletFormsElementos() {
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
			this.alta(request, response);
			break;
			
		case "modificacion":
			this.modificacion(request, response);
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
			request.setAttribute("urlcancelar", request.getHeader("Referer"));
			this.setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormElemento.jsp?accion=baja").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
		
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("proxid", new CtrlElementoLogic().getMaxId()+1);
			request.setAttribute("urlcancelar", request.getHeader("Referer"));
			request.getRequestDispatcher("WEB-INF/FormElemento.jsp?accion=alta").forward(request, response);
		} catch (Exception  e) {
			error(request,response,e);
		}
	}
	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setAttribute("urlcancelar", request.getHeader("Referer"));
			setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormElemento.jsp?accion=modificacion").forward(request, response);
	
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}	

		
	}

	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("id", request.getParameter("id")==null?"":request.getParameter("id"));
		request.setAttribute("nombre", request.getParameter("nombre")==null?"":request.getParameter("nombre"));
		request.setAttribute("tipoelemento", request.getParameter("tipoelemento")==null?"":request.getParameter("tipoelemento"));

	}
		
	


}
