package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlElementoLogic;
import business.logic.CtrlPersonaLogic;

/**
 * Servlet implementation class ServletListaElementos
 */
@WebServlet({ "/ServletListaElementos", "/servletlistaelementos", "/Servletlistaelementos" })
public class ServletListaElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaElementos() {
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

		try {
			CtrlElementoLogic ctrl= new CtrlElementoLogic();	
			request.setAttribute("listaElementos", ctrl.getAll());
			request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("Error",e);
			request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);
		}		
		
	}

}
