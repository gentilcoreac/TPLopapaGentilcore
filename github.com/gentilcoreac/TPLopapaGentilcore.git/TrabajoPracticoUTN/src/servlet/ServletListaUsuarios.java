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
 * Servlet implementation class listaUsuarios
 */
@WebServlet({ "/ServletListaUsuarios", "/servletlistausuarios", "/servletListaUsuarios" })
public class ServletListaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaUsuarios() {
        super();
        // TODO Auto-generated constructor stub
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

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ESTA ES UNA PRUEBA PARA PODER SOLICITAR EL LISTADO ESTANDO DESDE CUALQUIER PAGINA DEL SISTEMA. PROBE LLAMANDOLO DESDE UN FORM QUE ESTABA EN LA NAVBAR///
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		try {
			CtrlPersonaLogic ctrl= new CtrlPersonaLogic();	
			request.setAttribute("listaPersonas", ctrl.getAll());
			request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("Error",e);
			request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);
		}		
	}
}
