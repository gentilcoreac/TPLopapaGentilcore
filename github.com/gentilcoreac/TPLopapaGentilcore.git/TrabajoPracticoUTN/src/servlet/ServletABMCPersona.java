package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.entities.Categoria;
import business.entities.Persona;
import business.logic.CtrlPersonaLogic;

/**
 * Servlet implementation class ABMCPersona
 */
@WebServlet({ "/ServletABMCPersona", "/servletabmcpersona", "/ServletAbmcpersona",  "/servletabmcPersona", "/ServletAbmcPersona", "/SERVLETABMCPERSONA", "/servletABMCpersona" })
public class ServletABMCPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletABMCPersona() {
    	super();
    //   	logger = LogManager.getLogger(getClass());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//Persona pers=ctrl.getLoggedUser(user,pass); este se podria usar solo para obtener el usuario y si es admin puede ingresar. Pienso al aire				
		Persona per=new Persona();
				
				per.setUsuario(request.getParameter("usuario"));
				per.setContrasenia(request.getParameter("contrasenia"));
				Categoria cat = new Categoria();
				cat.setId(Integer.parseInt(request.getParameter("categoria")));
				per.setCategoria(cat);
				per.setApellido(request.getParameter("apellido"));		
				per.setNombre(request.getParameter("nombre"));
				per.setDni(request.getParameter("dni"));
				per.setHabilitado(Boolean.parseBoolean(request.getParameter("habilitado")));
				per.setEmail(request.getParameter("email"));
				
				
		CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		
		try {
			
			ctrl.add(per);
			request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response); 	//webinf o para mantener los atributos input
			

		} catch (Exception e) {

			//request.getRequestDispatcher("Errores/PaginaDeErrores.jsp").forward(request, response);
			response.sendRedirect("Errores/PaginaDeErrores.jsp");
		}


	//	doGet(request, response);
	}

}
