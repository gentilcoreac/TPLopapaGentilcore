package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/ServletLogout","/SERVLETLOGOUT","/servletlogout"})
public class ServletLogout extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
    
    public ServletLogout() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			request.getSession().invalidate();//pone null todos los atributos de la sesion
			request.getRequestDispatcher("WEB-INF/Logout.jsp").forward(request,response);
		}
		catch(Exception ex){
			error(request, response);	
		}
	}

}
