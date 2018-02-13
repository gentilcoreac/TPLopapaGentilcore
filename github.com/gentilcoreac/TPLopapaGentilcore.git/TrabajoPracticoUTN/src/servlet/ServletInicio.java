package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/ServletInicio","/SERVLETINICIO","/servletinicio"})
public class ServletInicio extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
  
    public ServletInicio() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			request.getRequestDispatcher("WEB-INF/Inicio.jsp").forward(request, response);
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}

}
