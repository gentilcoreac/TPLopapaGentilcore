package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlPersonaLogic;


@WebServlet({"/ServletListaUsuarios","/servletlistausuarios"})
public class ServletListaUsuarios extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
  
    public ServletListaUsuarios() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			CtrlPersonaLogic ctrl= new CtrlPersonaLogic();	
			request.setAttribute("listaPersonas", ctrl.getAll());
			request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);
		}
		catch(Exception ex){
				error(request, response, ex);
		}
	}

}
