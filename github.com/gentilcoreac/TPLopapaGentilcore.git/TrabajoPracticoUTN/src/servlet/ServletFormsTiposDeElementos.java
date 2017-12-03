package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/ServletFormsTiposDeElementos","/servletformstiposdeelementos","/SERVLETFORMSTIPOSDEELEMENTOS"})
public class ServletFormsTiposDeElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
  
    public ServletFormsTiposDeElementos() {
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
			request.getRequestDispatcher("WEB-INF/FormTipoDeElemento.jsp?accion=baja").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
		
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("urlcancelar", request.getHeader("Referer"));
			request.getRequestDispatcher("WEB-INF/FormTipoDeElemento.jsp?accion=alta").forward(request, response);
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}
	}
	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setAttribute("urlcancelar", request.getHeader("Referer"));
			setearAtributos(request);
			request.getRequestDispatcher("WEB-INF/FormTipoDeElemento.jsp?accion=modificacion").forward(request, response);
	
		} catch (ServletException | IOException e) {
			error(request,response,e);
		}	

		
	}

	private void setearAtributos(HttpServletRequest request) {
		
		request.setAttribute("id", request.getParameter("id")==null?"":request.getParameter("id"));
		request.setAttribute("nombre", request.getParameter("nombre")==null?"":request.getParameter("nombre"));
		request.setAttribute("cant_max_res_pen", request.getParameter("cant_max_res_pen")==null?"":request.getParameter("cant_max_res_pen"));
		request.setAttribute("limite_horas_res", request.getParameter("limite_horas_res")==null?"":request.getParameter("limite_horas_res"));
		request.setAttribute("dias_max_anticipacion", request.getParameter("dias_max_anticipacion")==null?"":request.getParameter("dias_max_anticipacion"));
		request.setAttribute("only_encargados", request.getParameter("only_encargados")==null?"":request.getParameter("only_encargados"));
	}

}
