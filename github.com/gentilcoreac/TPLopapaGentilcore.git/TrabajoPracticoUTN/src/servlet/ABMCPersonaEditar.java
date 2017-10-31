package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Categoria;
import business.entities.Persona;
import business.logic.CtrlPersonaLogic;

/**
 * Servlet implementation class ABMCPersonaEditar
 */
@WebServlet("/ABMCPersonaEditar")
public class ABMCPersonaEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCPersonaEditar() {
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
		
		Persona per = new Persona();
		//System.out.println("El valor es en el servlet"+  Integer.parseInt(request.getParameter("editar")));
		per.setDni(request.getParameter("DNIeditar"));
		per.setApellido(request.getParameter("ApellidoEditar"));
		Categoria cat = new Categoria();
		cat.setId(Integer.parseInt(request.getParameter("CategoriaEditar"))); 
		per.setContrasenia(request.getParameter("ContraseniaEditar"));
		per.setEmail(request.getParameter("EmailEditar"));
		per.setHabilitado(Boolean.parseBoolean(request.getParameter("HabilitadoEditar")));
		per.setId(Integer.parseInt(request.getParameter("IdEditar")));
		per.setNombre(request.getParameter("NombreEditar"));
		per.setUsuario(request.getParameter("UsuarioEditar"));
		
		CtrlPersonaLogic ctrl= new CtrlPersonaLogic();		
			try {
				ctrl.update(per);
				//System.out.println("Persona ID editada"+  request.getParameter("editar"));
				//request.getRequestDispatcher("WEB-INF/ListaUsuario.jsp").forward(request, response);
				request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response);
		
			} catch (Exception e) {
				response.sendRedirect("Errores/PaginaDeErrores.jsp");

				//-------------->>>>>>>>>>REDIRIGIRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
			}
	}
}
