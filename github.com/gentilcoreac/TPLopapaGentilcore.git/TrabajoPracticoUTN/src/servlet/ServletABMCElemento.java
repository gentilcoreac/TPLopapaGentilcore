package servlet;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import business.entities.TipoDeElemento;
import business.entities.Elemento;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlTipoDeElementoLogic;;

/**
 * Servlet implementation class ServletABMCElemento
 */
@WebServlet("/ServletABMCElemento")
public class ServletABMCElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;       
	CtrlElementoLogic ctrl= new CtrlElementoLogic();	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletABMCElemento() {
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
		// TODO Auto-generated method stub

	//	Persona user_logueado = (Persona) request.getSession().getAttribute("user");
	//	if(user_logueado.getCategoria().getId().equals())
		switch (request.getParameter("opcion")) {
		case "eliminar":
			try {
				this.baja(request,response);
				request.setAttribute("listaElementos", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp");
			}
			break;

		case "buscarPeditar":
			try {
				this.consulta(request,response);
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp");
			}
			break;	
			
		case "buscarPCrear":
			try {
				this.consultaParaReserva(request,response);
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp");
			}
			break;				

		case "editar":
			try {
				this.modificacion(request,response);
				request.setAttribute("listaElementos", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp");
			}
			break;

		case "agregar":
			try {
				this.alta(request,response);
				request.setAttribute("listaElementos", ctrl.getAll());
				request.getRequestDispatcher("WEB-INF/ListaElementos.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e);
				request.getRequestDispatcher("WEB-INF/Informes.jsp");
			}
			break;
			//Persona pers=ctrl.getLoggedUser(user,pass); este se podria usar solo para obtener el usuario y si es admin puede ingresar. Pienso al aire				

		default:
			this.error(request,response);
			break;
		}
	}

	private void error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
		//redirigir a página de error

	}


	private void consulta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
		
		//CtrlElementoLogic ctrl= new CtrlElementoLogic();		
			Elemento e = ctrl.getOne(Integer.parseInt(request.getParameter("IDPEditar")));
			request.setAttribute("ele_ret", e);
			request.getRequestDispatcher("WEB-INF/EditarElemento.jsp").forward(request, response);		
		
	}
	
	private void consultaParaReserva(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
		
		//CtrlElementoLogic ctrl= new CtrlElementoLogic();		
			Elemento e = ctrl.getOne(Integer.parseInt(request.getParameter("IDPCrear")));
			request.setAttribute("ele_ret", e);
			request.getRequestDispatcher("WEB-INF/EditarElemento.jsp").forward(request, response);		
		
	}


	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
		
		Elemento ele=new Elemento();
		ele.setId_elemento(Integer.parseInt(request.getParameter("id_elemento")));
		ele.setNombre(request.getParameter("nombre"));
		TipoDeElemento tip = new TipoDeElemento();
		tip.setId(Integer.parseInt(request.getParameter("tipo_elemento")));
		ele.setTipo(tip);
		
		//CtrlElementoLogic ctrl= new CtrlElementoLogic();		
		ctrl.update(ele);
		//	request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response); 	//webinf o para mantener los atributos input
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
	
		Elemento ele=new Elemento();
		ele.setId_elemento(Integer.parseInt(request.getParameter("ID_eliminar")));
		//CtrlElementoLogic ctrl= new CtrlElementoLogic();		

		ctrl.delete(ele);
		//		request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response);
	}



	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		Elemento ele=new Elemento();
				
			//ele.setId_elemento(Integer.parseInt(request.getParameter("id_elemento")));
			ele.setNombre(request.getParameter("nombre"));
			TipoDeElemento tip = new TipoDeElemento();
			tip.setId(Integer.parseInt(request.getParameter("tipo_elemento")));
			ele.setTipo(tip);
		
	//	CtrlElementoLogic ctrl= new CtrlElementoLogic();		

			ctrl.add(ele);
		//	request.getRequestDispatcher("WEB-INF/pruebaConFecha.jsp").forward(request, response); 	//webinf o para mantener los atributos input
		
	
		
	}

}
