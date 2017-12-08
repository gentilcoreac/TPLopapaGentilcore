package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.logic.CtrlReservaLogic;

/**
 * Servlet implementation class ServletABMCReserva
 */
@WebServlet({ "/ServletABMCReserva", "/Servletabmcreserva", "/servletabmcreserva", "/ServletABMCReservas" })
public class ServletABMCReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CtrlReservaLogic ctrl = new CtrlReservaLogic();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletABMCReserva() {
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

		Persona user_logueado = (Persona) request.getSession().getAttribute("user");
		//	if(user_logueado.getCategoria().getId().equals())
			switch (request.getParameter("opcion")) {
			case "eliminar":
				try {
					this.baja(request,response);
				request.setAttribute("listaReservas", ctrl.getAll());
					request.getRequestDispatcher("WEB-INF/ListaReservas.jsp").forward(request, response);
			
				}catch (Exception e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
					}
				break;

			case "buscarPeditar":
				try {
					this.consulta(request,response);
				}catch (Exception e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
					}
				break;			

			case "editar":
				try {
					this.modificacion(request,response);
					request.setAttribute("listaReservas", ctrl.getAll());
					request.getRequestDispatcher("WEB-INF/ListaReservas.jsp").forward(request, response);
				}catch (Exception e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
					}
				break;

			case "agregar":
				try {
					this.alta(request,response);
					request.setAttribute("listaReservas", ctrl.getAll());
					request.getRequestDispatcher("WEB-INF/ListaReservas.jsp").forward(request, response);			
				}catch (Exception e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
					}
				break;
				//Persona pers=ctrl.getLoggedUser(user,pass); este se podria usar solo para obtener el usuario y si es admin puede ingresar. Pienso al aire
			case"buscarPCrear"://///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				try {this.alta(request,response);
					
				}catch (Exception e) {
					request.setAttribute("error", e);
					request.getRequestDispatcher("WEB-INF/Informes.jsp").forward(request, response);		
					}
				break;
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
				
				Reserva r = new Reserva();
				r.setId_reserva(Integer.parseInt(request.getParameter("idReservaPeditar")));
				
				r = ctrl.getOne(r);
				
				request.setAttribute("res_ret", r);
				request.getRequestDispatcher("WEB-INF/EditarReserva.jsp").forward(request, response);		
		}


		private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
			Reserva res=new Reserva();
			res.setId_reserva(Integer.parseInt(request.getParameter("id_reserva")));
			
			Persona per = new Persona();
			per.setId(Integer.parseInt(request.getParameter("persona")));
			res.setPersona(per);
			Elemento ele = new Elemento();
			ele.setId_elemento(Integer.parseInt(request.getParameter("id_elemento")));
			res.setElemento(ele);

			String fechaHoraE = request.getParameter("resentregado");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:SS");   //"yyyy-mm-dd HH:mm:ss"
			formatter.setLenient(false);		
			res.setFecha_hora_entregado(formatter.parse(fechaHoraE));
		
			
			//res.setFecha_hora_entregado(request.getParameter("resentregado"));
			 
			
				ctrl.updateParaCerrarRes(res);
			
		}



		private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
			response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");		
			Reserva res= new Reserva();
			res.setId_reserva(Integer.parseInt(request.getParameter("idReservaeliminar")));
					
				ctrl.delete(res);				
		}

		private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
			Reserva res=new Reserva();
		//	res.setId_reserva(Integer.parseInt(request.getParameter("id_reserva")));
			res.setDetalle(request.getParameter("detalle"));
	/*		res.setFecha_hora_desde_solicitada(request.getParameter("desde"));
			res.setFecha_hora_hasta_solicitada(request.getParameter("hasta"));
			res.setFecha_hora_reserva_hecha(request.getParameter("reshecho"));
			res.setFecha_hora_entregado(request.getParameter("resentregado"));*/
		
			Persona per = new Persona();
			per.setId(Integer.parseInt(request.getParameter("persona")));
			res.setPersona(per);
			Elemento ele = new Elemento();
			ele.setId_elemento(Integer.parseInt(request.getParameter("id_elemento")));
			res.setElemento(ele);
			//FORMATEO FECHA
			String fechaHoraHecha = request.getParameter("reshecho");
			SimpleDateFormat formatterHec = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");   //"yyyy-mm-dd HH:mm:ss"
			formatterHec.setLenient(false);		
			res.setFecha_hora_reserva_hecha(formatterHec.parse(fechaHoraHecha));
					
			String fechaHoraDesde = request.getParameter("resdesde");
			SimpleDateFormat formatterDes = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");   //"yyyy-mm-dd HH:mm:ss"
			formatterDes.setLenient(false);		
			res.setFecha_hora_reserva_hecha(formatterDes.parse(fechaHoraDesde));
			
			String fechaHoraHasta = request.getParameter("reshasta");
			SimpleDateFormat formatterHas = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");   //"yyyy-mm-dd HH:mm:ss"
			formatterHas.setLenient(false);		
			res.setFecha_hora_reserva_hecha(formatterHas.parse(fechaHoraHasta));
			
			//id_reserva no deberia aparecer sólo?
			//persona
			//id_elemento
			//elemento
			//reshecho
			//resdesde
			//reshasta
				ctrl.add(res);		
		}
}
