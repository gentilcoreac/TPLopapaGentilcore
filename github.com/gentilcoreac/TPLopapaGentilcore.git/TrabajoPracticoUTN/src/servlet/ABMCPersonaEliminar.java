package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Categoria;
import business.entities.Persona;
import business.logic.CtrlPersonaLogic;

/**
 * Servlet implementation class ABMCPersonaEliminar
 */
@WebServlet("/ABMCPersonaEliminar")
public class ABMCPersonaEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCPersonaEliminar() {
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
		// TODO Auto-generated method stub
	
		Persona per = new Persona();
		//System.out.println("El valor es en el servlet"+  Integer.parseInt(request.getParameter("eliminar")));
		per.setId(Integer.parseInt(request.getParameter("eliminar")));
		
		CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		
			try {
				ctrl.delete(per);
				//System.out.println("Persona ID eliminada"+  request.getParameter("eliminar"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//-------------->>>>>>>>>>REDIRIGIRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

			}
	//request.getRequestDispatcher("WEB-INF/ListaUsuario.jsp").forward(request, response);
			request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response);

 	}

}
