package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet({"/ServletFormsUsuarios","/servletformsusuarios","/SERVLETFORMSUSUARIOS"})
public class ServletFormsUsuarios extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
 
    public ServletFormsUsuarios() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			this.setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormUsuario.jsp?accion=baja").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
		
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/FormUsuario.jsp?accion=alta").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
	}
	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormUsuario.jsp?accion=modificacion").forward(request, response);
	
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}	

		
	}

	private void setearAtributos(HttpServletRequest request) {
		request.setAttribute("id", request.getParameter("id")==null?"":request.getParameter("id"));
		request.setAttribute("dni", request.getParameter("dni")==null?"":request.getParameter("dni"));
		request.setAttribute("apellido", request.getParameter("apellido")==null?"":request.getParameter("apellido"));
		request.setAttribute("nombre", request.getParameter("nombre")==null?"":request.getParameter("nombre"));
		request.setAttribute("usuario", request.getParameter("usuario")==null?"":request.getParameter("usuario"));
		request.setAttribute("contrasenia", request.getParameter("contrasenia")==null?"":request.getParameter("contrasenia"));
		request.setAttribute("email", request.getParameter("email")==null?"":request.getParameter("email"));
		request.setAttribute("categoria", request.getParameter("categoria")==null?"":request.getParameter("categoria"));
		request.setAttribute("habilitado", request.getParameter("habilitado")==null?"":request.getParameter("habilitado"));
	}
		
	

}
