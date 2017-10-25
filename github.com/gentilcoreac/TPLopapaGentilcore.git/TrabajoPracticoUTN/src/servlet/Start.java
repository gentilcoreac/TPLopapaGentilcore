package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Persona;
import business.logic.CtrlPersonaLogic;
import tools.AppDataException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;


/**
 * Servlet implementation class Starts
 */
@WebServlet({"/Start","/start"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;

    /**

     * Default constructor. 
     * @return 

     */

    public void Start() {
    	logger = LogManager.getLogger(getClass());
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
					//todo lo qe me mandan dentro del form esta en el request
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");

	//		Persona per=new Persona();
	//		per.setUsuario(user);
	//		per.setContrasenia(pass);

			CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		

			Persona pers=ctrl.getLoggedUser(user,pass);			
			
			if(pers==null){		//en caso que no exista el que se ingresó
				request.getRequestDispatcher("Errores/UsuarioNoEncontrado.jsp").forward(request, response);								
			}
			else{
			
			request.setAttribute("listaPersonas", ctrl.getAll());
			request.getSession().setAttribute("user", pers);		//1 atributo: user es un atributo q yo creo
																	//2 parametro: es un objeto java(debe ser serializable y javabin)

		/*   *todo lo que tenga que durar en muchas paginas, lo guardo en el servidor. El usuario logueado , que debe durar durante toda la sesion, se lo asigno al servidor
			 * Si guardo un listado de reservas en la sesion, cuando haya miles de usuarios a la vez, saturaré el servidor
			 * En cambio los datos que se van a usar en la proxima pagina lo seteo como atributo. Ahorro memoria, y no tengo inconvenientes. Obviamente , solo se usa en la pagina siguiente nada mas.
			 * 
		*/
			request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);

			}
			//response.getWriter().append(user).append(" ").append(pass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}
	
}
