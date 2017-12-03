package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlReservaLogic;

/**
 * Servlet implementation class ServletListaReservas
 */
@WebServlet({ "/ServletListaReservas", "/servletlistareservas" })
public class ServletListaReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaReservas() {
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
			CtrlReservaLogic ctrl= new CtrlReservaLogic();	
			request.setAttribute("listaReservas", ctrl.getAll());
			request.getRequestDispatcher("WEB-INF/ListaReservas.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("Error",e);
			request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);
		}		
	}

}
