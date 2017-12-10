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
import business.entities.TipoDeElemento;
import business.logic.CtrlElementoLogic;
import business.logic.CtrlPersonaLogic;
import business.logic.CtrlReservaLogic;
import business.logic.CtrlTipoDeElementoLogic;
import tools.Campo;


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
		
			if(Campo.Valida(request.getParameter("id"), Campo.tipo.ID)){
				Elemento ele=new Elemento();
				ele.setId_elemento(Integer.parseInt(request.getParameter("id")));
				ele.setNombre(request.getParameter("nombre"));
				TipoDeElemento te=new TipoDeElemento();
				te.setNombre(request.getParameter("tipo"));
				ele.setTipo(new CtrlTipoDeElementoLogic().getByName(te));
				CtrlElementoLogic ctrl= new CtrlElementoLogic();
				ctrl.update(ele);
				hacerInforme(request, response, TipoInforme.EXITO , "Elemento", "Datos de elemento actualizados correctamente","ServletListaElementos");			

			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Elemento", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
	}


	private void alta(HttpServletRequest request, HttpServletResponse response) {
		
		try {	
				if(validaCampos(request.getParameter("idpersona"),request.getParameter("idelemento"),
						       request.getParameter("fechareservadesde"),request.getParameter("fechareservahasta"))){
					Elemento elemento=new CtrlElementoLogic().getOne(Integer.parseInt(request.getParameter("idelemento")));
					CtrlReservaLogic ctrl=new CtrlReservaLogic();
					if(null!=elemento){
						Persona persona=new CtrlPersonaLogic().getOne(Integer.parseInt(request.getParameter("idpersona")));
						if(null!=persona){
							Date fd=Campo.parseaFecha(request.getParameter("fechareservadesde"));
							Date fh=Campo.parseaFecha(request.getParameter("fechareservahasta"));
							String errores=ctrl.retornaErroresFechas(fd,fh,elemento);  
							if(errores==null){
										Reserva res=new Reserva();
										res.setPersona(persona);
										res.setElemento(elemento);
										res.setFecha_hora_desde_solicitada(fd);
										res.setFecha_hora_hasta_solicitada(fh);
										SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
										res.setFecha_hora_reserva_hecha(formatter.parse(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime())));
										res.setDetalle(request.getParameter("detalle"));
										if(ctrl.sePuedeCrear(persona, res)){
											if(!ctrl.hayLimtResPen(persona, res)){
												ctrl.add(res);
												hacerInforme(request, response, TipoInforme.EXITO , "Reserva", "Reserva creada correctamente","ServletListaReservas");			
											}
											else{
												hacerInforme(request, response, TipoInforme.INFO , "Reserva", "No se pueden reservar mas elementos de este tipo\n"
														                                                   + "Limite de reservas pendientes alcanzadas para el tipo:"
														                                                   + res.getElemento().getTipo().getNombre());			
											}
										}
										else{
											hacerInforme(request, response, TipoInforme.INFO , "Reserva", "Solo los encargados pueden reservar este tipo de elemento");			
										}

									}
							  else{
									hacerInforme(request, response, TipoInforme.INFO , "Reserva", errores);			
							  }
							}
						else{
							hacerInforme(request, response, TipoInforme.INFO , "Reserva", "La persona no existe");			
						}
					}
					else{
						hacerInforme(request, response, TipoInforme.INFO , "Reserva", "El elemento no existe");			
					}
				}
				else{
					hacerInforme(request, response, TipoInforme.INFO , "Reserva", Campo.getMensaje());			
				}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}
	
	private boolean validaCampos(String idPer,String idEle,String fhD,String fhH){
		return (Campo.Valida(idPer, Campo.tipo.ID)&& Campo.Valida(idEle, Campo.tipo.ID) 
				&& Campo.Valida(fhD,Campo.tipo.FECHAHORA) && Campo.Valida(fhH,Campo.tipo.FECHAHORA));
	}


}
