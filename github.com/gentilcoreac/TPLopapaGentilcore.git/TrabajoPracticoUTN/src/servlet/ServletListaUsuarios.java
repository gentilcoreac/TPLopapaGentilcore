package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Persona;
import business.logic.CtrlPersonaLogic;
import tools.Campo;


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
			if(request.getParameter("accion")!=null && request.getParameter("accion").equals("consulta") && ((Persona)request.getSession().getAttribute("user")).getCategoria().getDescripcion().equals("Administrador")){
				ArrayList<Persona> persona=new ArrayList<Persona>();
				persona.add(ctrl.getByDni(request.getParameter("dni")));
				request.setAttribute("listaPersonas", persona);
				//todo esto a fin de buscar una persona desde la lista de reservas
			}
			else{
				request.setAttribute("listaPersonas", ctrl.getAll());
			}
			request.getRequestDispatcher("WEB-INF/ListaUsuarios.jsp").forward(request, response);
		}
		catch(Exception ex){
				error(request, response, ex);
		}
	}

}
