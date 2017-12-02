package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.entities.Persona;
import business.logic.CtrlPersonaLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;



/**
 * Servlet implementation class Starts
 */
@WebServlet({"/Start","/start"})
public class Start extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
	private Logger logger;

    /**
     * Default constructor. 
     */

    public Start() {
    	logger = LogManager.getLogger(getClass());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					//todo lo qe me mandan dentro del form esta en el request
		try{
			if(request.getParameter("username")!=null){
				String user=request.getParameter("username");
				String pass=request.getParameter("password");
				CtrlPersonaLogic ctrl=new CtrlPersonaLogic();
				Persona usu=ctrl.getLoggedUser(user, pass);
				if(usu!=null){
					if(usu.isHabilitado()==true){
						request.getSession().setAttribute("user", usu);		//1 atributo: user es un atributo q yo creo
																				//2 parametro: es un objeto java(debe ser serializable y javabin)
	
					/*   *todo lo que tenga que durar en muchas paginas, lo guardo en el servidor. El usuario logueado , que debe durar durante toda la sesion, se lo asigno al servidor
						 * Si guardo un listado de reservas en la sesion, cuando haya miles de usuarios a la vez, saturar� el servidor
						 * En cambio los datos que se van a usar en la proxima pagina lo seteo como atributo. Ahorro memoria, y no tengo inconvenientes. Obviamente , solo se usa en la pagina siguiente nada mas.
						 * 
					*/
						logger.log(Level.INFO,"log in "+usu.getDni());
						request.getRequestDispatcher("WEB-INF/Inicio.jsp").forward(request, response);
					}else{
						 this.hacerInforme(request, response, TipoInforme.INFO, "Info", "El usuario no se halla habilitado para ingresar al sistema");
						 }
				}
				else{
					this.hacerInforme(request, response, TipoInforme.INFO, "Info", "Usuario no encontrado");
					}
			}
		else{
			request.getRequestDispatcher("WEB-INF/Inicio.jsp").forward(request, response);

			}
		}
		catch(Exception ex){
			this.error(request, response, ex);	
		}

	}
	
	
}
