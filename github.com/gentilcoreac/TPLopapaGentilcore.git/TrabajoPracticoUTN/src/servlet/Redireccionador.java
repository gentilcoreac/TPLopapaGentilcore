package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Redireccionador
 */
@WebServlet("/Redireccionador")
public class Redireccionador extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Redireccionador() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		try{
			if(String.valueOf(request.getParameter("logout")).equals("si")){
	
	
				request.getSession().invalidate();
				request.getRequestDispatcher("WEB-INF/Logout.jsp").forward(request,response);
			
			}
			else{
				String destino=String.valueOf(request.getParameter("destino"));
				request.getRequestDispatcher(destino).forward(request,response);
			}
		}
		catch(Exception ex){
			error(request, response, ex);	
		}
	}

}
