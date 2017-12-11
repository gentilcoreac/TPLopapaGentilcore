package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/ServletPerfil","/servletperfil","/SERVLETPERFIL"})
public class ServletPerfil extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
    
    public ServletPerfil() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			request.getRequestDispatcher("WEB-INF/Perfil.jsp").forward(request,response);
			
		}
		catch(Exception ex){
			error(request, response, ex);	
		}
	}

}
