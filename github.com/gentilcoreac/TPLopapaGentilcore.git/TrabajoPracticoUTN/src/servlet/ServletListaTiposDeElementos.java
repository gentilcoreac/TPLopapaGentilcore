package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.logic.CtrlTipoDeElementoLogic;


@WebServlet({"/ServletListaTiposDeElementos","/servletlistatiposdeelementos"})
public class ServletListaTiposDeElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       

    public ServletListaTiposDeElementos() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			CtrlTipoDeElementoLogic ctrl=new CtrlTipoDeElementoLogic();
			request.setAttribute("listaTipos", ctrl.getAll());
			request.getRequestDispatcher("WEB-INF/ListadoTiposDeElementos.jsp").forward(request, response);
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}

}
