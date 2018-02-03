package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import business.entities.Persona;
import business.logic.CtrlCategoriaLogic;
import business.logic.CtrlPersonaLogic;
import tools.Campo;
import tools.Emailer;


@WebServlet({ "/ServletABMCPersona/*", "/servletabmcpersona/*", "/ServletAbmcpersona/*",  "/servletabmcPersona/*", "/ServletAbmcPersona/*", "/SERVLETABMCPERSONA/*", "/servletABMCpersona/*" })
public class ServletABMCPersona extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;

    public ServletABMCPersona() {
    	super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		switch (request.getParameter("accion")) {
		case "alta":
			this.alta(request,response);
			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "modificacion":
			this.modificacion(request,response);
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
		
		if(Campo.Valida(request.getParameter("dni"), Campo.tipo.DNI)){
			try {			
				Persona per=new CtrlPersonaLogic().getByDni(request.getParameter("dni"));
				if(per==null){
					hacerInforme(request, response, TipoInforme.INFO , "Usuario", "No existe ninguna persona con el dni "+request.getParameter("dni"));			
	
				}
				else{
					request.setAttribute("id", String.valueOf(per.getId()));
					request.setAttribute("dni", per.getDni());
					request.setAttribute("apellido", per.getApellido());
					request.setAttribute("nombre", per.getNombre());
					request.setAttribute("usuario", per.getUsuario());
					request.setAttribute("contrasenia", per.getContrasenia());
					request.setAttribute("email", per.getEmail());
					request.setAttribute("categoria", per.getCategoria().getDescripcion());
					request.setAttribute("habilitado", String.valueOf(per.isHabilitado()));
					request.getRequestDispatcher("WEB-INF/FormUsuario.jsp?accion="+request.getParameter("fin")).forward(request, response);
				}
				
				
			} catch (Exception ex) {
			
				this.error(request, response, ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Usuario", Campo.getMensaje());			
		}
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		
		if(Campo.Valida(request.getParameter("dni"), Campo.tipo.DNI)){
			try{

				CtrlPersonaLogic ctrl =new CtrlPersonaLogic();
				Persona per=ctrl.getByDni(request.getParameter("dni"));
				if(per==null){
					hacerInforme(request, response, TipoInforme.INFO , "Usuario", "No existe ninguna persona con el dni "+request.getParameter("dni"));			
	
				}
				else{
					ctrl.delete(per);
					logger.log(Level.INFO,"Persona eliminada Dni:"+per.getDni()+" user:"+per.getUsuario());
					try{
						Emailer.getInstance().send(per.getEmail(), "MyReserva-Su usuario ha sido eliminado", "Datos del usuario eliminado"+per.toString());
					}
					catch(Exception ex){
						throw new Exception("Persona eliminada correctamente.Se produjo un error de Email:"+ex.getMessage());
					}
					hacerInforme(request, response, TipoInforme.EXITO , "Usuario", "Persona eliminada correctamente","ServletListaUsuarios");			
				}
			}
			catch(Exception ex){
				this.error(request, response,ex);
			}
		}
		else{
			hacerInforme(request, response, TipoInforme.INFO , "Usuario", Campo.getMensaje());			
		}
	}


	private void modificacion(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		
			if(this.validaCampos(request.getParameter("dni"), request.getParameter("email"))){
				CtrlPersonaLogic ctrl =new CtrlPersonaLogic();
				Persona per=ctrl.getByDni(request.getParameter("dni"));
				if(per==null){
					hacerInforme(request, response, TipoInforme.INFO , "Usuario", "No existe ninguna persona con el dni "+request.getParameter("dni"));			
	
				}
				else{
					//per.setId(Integer.parseInt(request.getParameter("id")));
					per.setUsuario(request.getParameter("usuario"));
					per.setContrasenia(request.getParameter("contrasenia"));
					per.setCategoria(new CtrlCategoriaLogic().getOne(request.getParameter("categoria")));
					per.setApellido(request.getParameter("apellido"));		
					per.setNombre(request.getParameter("nombre"));
					per.setDni(request.getParameter("dni"));
					per.setHabilitado(request.getParameter("habilitado")==null?false:true);
					per.setEmail(request.getParameter("email"));
					ctrl.update(per);
					logger.log(Level.INFO,"Persona modificada id:"+per.getId());
					hacerInforme(request, response, TipoInforme.EXITO , "Usuario", "Datos de persona actualizados correctamente","ServletListaUsuarios");			
				}
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Usuario", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
	}


	private void alta(HttpServletRequest request, HttpServletResponse response) {
		
		try {	
			if(this.validaCampos(request.getParameter("dni"), request.getParameter("email"))){
				Persona per=new Persona();
				per.setUsuario(request.getParameter("usuario"));
				per.setContrasenia(request.getParameter("contrasenia"));
				per.setCategoria(new CtrlCategoriaLogic().getOne(request.getParameter("categoria")));
				per.setApellido(request.getParameter("apellido"));		
				per.setNombre(request.getParameter("nombre"));
				per.setDni(request.getParameter("dni"));
				per.setHabilitado(request.getParameter("habilitado")==null?false:true);
				per.setEmail(request.getParameter("email"));

				CtrlPersonaLogic ctrl= new CtrlPersonaLogic();
				ctrl.add(per);
				logger.log(Level.INFO,"Persona agregada Dni:"+per.getDni()+" user:"+per.getUsuario());
				try{
					Emailer.getInstance().send(per.getEmail(), "Bienvenido a MyReserva", "Usted ya forma parte del mejor sistema del universo\n\n"+per.toString());
				}
				catch(Exception ex){
					throw new Exception("Persona agregada correctamente.Se produjo un error de Email:"+ex.getMessage());
				}
				hacerInforme(request, response, TipoInforme.EXITO , "Usuario", "Persona agregada correctamente","ServletListaUsuarios");			
			}
			else{
				hacerInforme(request, response, TipoInforme.INFO , "Usuario", Campo.getMensaje());			
			}
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}
	
	private boolean validaCampos(String dni,String email) {

		
		return (Campo.Valida(dni, Campo.tipo.DNI) && 
		   Campo.Valida(email, Campo.tipo.EMAIL));
		 

	}

}
