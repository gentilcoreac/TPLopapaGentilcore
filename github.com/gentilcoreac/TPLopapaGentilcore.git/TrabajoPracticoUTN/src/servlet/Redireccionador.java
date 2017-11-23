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
public class Redireccionador extends HttpServlet {
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
		try{
		doPost(request, response);
		}
		catch(Exception ex){
			request.setAttribute("Titulo", "Error");
			request.setAttribute("Mensaje", ex.getMessage());
			request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);
		}
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
			request.setAttribute("Titulo", "Error");
			request.setAttribute("Mensaje", ex.getMessage());
			request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);	
		}
	}

}
