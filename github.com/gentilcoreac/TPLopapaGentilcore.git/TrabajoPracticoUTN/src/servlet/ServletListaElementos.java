package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlElementoLogic;
import tools.Campo;


@WebServlet("/ServletListaElementos")
public class ServletListaElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
   
    public ServletListaElementos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			CtrlElementoLogic ctrl=new CtrlElementoLogic();
			request.setAttribute("listaElementos", ctrl.getSome(Campo.TipoBusquedaE.TRAER_TODOS, null, null));
			request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}

}
