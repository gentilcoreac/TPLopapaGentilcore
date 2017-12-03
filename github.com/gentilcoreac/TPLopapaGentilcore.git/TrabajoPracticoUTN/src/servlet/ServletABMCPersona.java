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
@WebServlet({ "/ServletABMCPersona", "/servletabmcpersona", "/servletABMCPersona", "/servletABMCpersona" })
public class ServletABMCPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;       
	CtrlPersonaLogic ctrl= new CtrlPersonaLogic();	

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
		
		Persona user_logueado = (Persona) request.getSession().getAttribute("user");
	//	if(user_logueado.getCategoria().getId().equals())
		switch (request.getParameter("opcion")) {
		case "eliminar":
			try {
				this.baja(request,response);
				request.setAttribute("listaPersonas", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);
		
			}catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
				}
			break;

		case "buscarPeditar":
			try {
				this.consulta(request,response);
			}catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
				}
			break;			

		case "editar":
			try {
				this.modificacion(request,response);
				request.setAttribute("listaPersonas", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);
			}catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
				}
			break;

		case "agregar":
			try {
				this.alta(request,response);
				request.setAttribute("listaPersonas", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);			
			}catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
				}
			break;
			//Persona pers=ctrl.getLoggedUser(user,pass); este se podria usar solo para obtener el usuario y si es admin puede ingresar. Pienso al aire				

		default:
			this.error(request,response);
			break;
		}	
	}

	
	
	
	
	
	private void error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
		//redirigir a página de error

	}


	private void consulta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
	//	CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		
		
			Persona p = ctrl.getByDni(request.getParameter("DNIPeditar"));
			request.setAttribute("user_ret", p);
			request.getRequestDispatcher("WEB-INF/EditarPersona.jsp").forward(request, response);		
			
	}


	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
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

			ctrl.update(per);
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
	
		Persona per = new Persona();
		per.setDni(request.getParameter("DNIeliminar"));
		
			ctrl.delete(per);
			
	}



	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
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
				
		//CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		
		
			ctrl.add(per);
			//request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response); 	//webinf o para mantener los atributos input
		
	}
		
	
		
}
