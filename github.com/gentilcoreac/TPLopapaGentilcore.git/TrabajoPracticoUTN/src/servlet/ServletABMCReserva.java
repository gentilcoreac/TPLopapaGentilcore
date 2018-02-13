package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Elemento;
import business.entities.Persona;
import business.entities.Reserva;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlPersonaLogic;
import business.logic.CtrlReservaLogic;
import tools.BookingException;
import tools.Campo;
import tools.Emailer;


@WebServlet({"/ServletABMCReserva","/SERVLETABMCRESERVA","/servletabmcreserva"})
public class ServletABMCReserva extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       
   
    public ServletABMCReserva() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		switch (request.getParameter("accion")) {
		case "alta":
			this.alta(request,response);
			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "cerrar":
			this.cerrar(request,response);
			break;
			
		case "consulta":
			this.consulta(request,response);
			break;

		default:
			this.error(request,response);
			break;
		}
	}
private void consulta(HttpServletRequest request, HttpServletResponse response) {
		
		if(Campo.Valida(request.getParameter("idreserva"), Campo.tipo.ID)){
			try {
				Reserva res=new CtrlReservaLogic().getOne(Integer.parseInt(request.getParameter("idreserva")),(Persona)request.getSession().getAttribute("user"));
				if(res==null){
					hacerInforme(request, response, TipoInforme.INFO , "Reserva", "No existe ninguna reserva con el id "+request.getParameter("idreserva"));			
	
				}
				else{
					request.setAttribute("idreserva",String.valueOf(res.getId_reserva()) );
					request.setAttribute("idpersona", String.valueOf(res.getPersona().getId()));
					request.setAttribute("idelemento", String.valueOf(res.getElemento().getId_elemento()));
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
					request.setAttribute("fechareservahecha",res.getFecha_hora_reserva_hecha()==null?null:formatter.format(res.getFecha_hora_reserva_hecha()) );
					request.setAttribute("fechareservadesde",res.getFecha_hora_desde_solicitada()==null?null:formatter.format(res.getFecha_hora_desde_solicitada()) );
					request.setAttribute("fechareservahasta", res.getFecha_hora_hasta_solicitada()==null?null:formatter.format(res.getFecha_hora_hasta_solicitada()));
					request.setAttribute("fechareservaentrega", res.getFecha_hora_entregado()==null?null:formatter.format(res.getFecha_hora_entregado()));
					request.setAttribute("detalle",res.getDetalle() );
					request.getRequestDispatcher("WEB-INF/FormReserva.jsp?accion="+request.getParameter("fin")).forward(request, response);
				}
				
				
			} catch (Exception ex) {
			
				this.error(request, response, ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Reserva", Campo.getMensaje());			
		}
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		
		if(Campo.Valida(request.getParameter("idreserva"), Campo.tipo.ID)){
			try{
				CtrlReservaLogic ctrl =new CtrlReservaLogic();
				Reserva res=new CtrlReservaLogic().getOne(Integer.parseInt(request.getParameter("idreserva")),(Persona)request.getSession().getAttribute("user"));
				if(res==null){
					hacerInforme(request, response, TipoInforme.INFO , "Reserva", "No existe ninguna reserva con el id "+request.getParameter("idreserva"));			
	
				}
				else{
						if(ctrl.sePuedeEliminar((Persona)request.getSession().getAttribute("user"), res)){
							ctrl.delete(res);
							try{
								Emailer.getInstance().send(res.getPersona().getEmail(), "MyReserva-Reserva Eliminada", "La siguiente reserva ha sido eliminada"+res.toString());
							}
							catch(Exception ex){
								throw new Exception("Reserva eliminada correctamente.Se produjo un error de Email:"+ex.getMessage());
							}
							hacerInforme(request, response, TipoInforme.EXITO , "Reserva", "Reserva eliminada correctamente","ServletListaReservas");			
						}
						else{
							hacerInforme(request, response, TipoInforme.INFO , "Reserva", "Solo se pueden eliminar reservas pendientes");			
						}
					}
			}
			catch(Exception ex){
				this.error(request, response,ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Reserva", Campo.getMensaje());			
		}
	}


	private void cerrar(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		
			 if(Campo.Valida(request.getParameter("idreserva"), Campo.tipo.ID)&&
			    Campo.Valida(request.getParameter("fechareservaentrega"), Campo.tipo.FECHAHORA)){
				 
				CtrlReservaLogic ctrl =new CtrlReservaLogic();
				Reserva res=new CtrlReservaLogic().getOne(Integer.parseInt(request.getParameter("idreserva")),(Persona)request.getSession().getAttribute("user"));
				Date fc=Campo.parseaFecha(request.getParameter("fechareservaentrega"));
				res.setFecha_hora_entregado(fc);
				ctrl.validaCierre(res);
				ctrl.updateParaCerrarRes(res);
				try{
					Emailer.getInstance().send(res.getPersona().getEmail(), "MyReserva-Reserva Cerrada", "La siguiente reserva ha sido cerrada"+res.toString());
				}
				catch(Exception ex){
					throw new Exception("Reserva cerrada correctamente.Se produjo un error de Email:"+ex.getMessage());
				}
				hacerInforme(request, response, TipoInforme.EXITO , "Reserva", "Reserva cerrada correctamente","ServletListaReservas");			
			
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Reserva", Campo.getMensaje());			
			}
		} catch(BookingException bex){
			hacerInforme(request, response, TipoInforme.INFO , "Reserva", bex.getMessage());
		}
		catch (Exception ex) {
		
			this.error(request, response, ex);
		}
	}


	private void alta(HttpServletRequest request, HttpServletResponse response) {
		
		try {	
				if(validaCampos(request.getParameter("idpersona"),request.getParameter("idelemento"),
						       request.getParameter("fechareservadesde"),request.getParameter("fechareservahasta"))){
					CtrlReservaLogic ctrl=new CtrlReservaLogic();
					Elemento elemento=new CtrlElementoLogic().getOne(Integer.parseInt(request.getParameter("idelemento")));
					Persona persona=new CtrlPersonaLogic().getOne(Integer.parseInt(request.getParameter("idpersona")));
					Date fd=Campo.parseaFecha(request.getParameter("fechareservadesde"));
					Date fh=Campo.parseaFecha(request.getParameter("fechareservahasta"));
					Reserva res=new Reserva();
					res.setPersona(persona);
					res.setElemento(elemento);
					res.setFecha_hora_desde_solicitada(fd);
					res.setFecha_hora_hasta_solicitada(fh);
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					res.setFecha_hora_reserva_hecha(formatter.parse(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime())));
					res.setDetalle(request.getParameter("detalle"));
					ctrl.validaAlta(res);
					ctrl.add(res);
					try{
					Emailer.getInstance().send(persona.getEmail(), "MyReserva-Reserva efectuada correctamente", "Nueva Reserva realizada"+res.toString());
					}
					catch(Exception ex){
						throw new Exception("Reserva creada correctamente.Se produjo un error de Email:"+ex.getMessage());
					}
					hacerInforme(request, response, TipoInforme.EXITO , "Reserva", "Reserva creada correctamente","ServletListaElementos");			
					
				}

		}catch(BookingException bex){
			hacerInforme(request, response, TipoInforme.INFO , "Reserva", bex.getMessage());
		} 
		catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}
	

	
	private boolean validaCampos(String idPer,String idEle,String fhD,String fhH){
		return (Campo.Valida(idPer, Campo.tipo.ID)&& Campo.Valida(idEle, Campo.tipo.ID) 
				&& Campo.Valida(fhD,Campo.tipo.FECHAHORA) && Campo.Valida(fhH,Campo.tipo.FECHAHORA));
	}


}
