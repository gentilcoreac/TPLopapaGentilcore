package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.entities.Categoria;
import business.entities.Persona;
import business.logic.CtrlCategoriaLogic;
import business.logic.CtrlPersonaLogic;
import tools.Campo;

/**
 * Servlet implementation class ABMCPersona
 */
@WebServlet({ "/ServletABMCPersona/*", "/servletabmcpersona/*", "/ServletAbmcpersona/*",  "/servletabmcPersona/*", "/ServletAbmcPersona/*", "/SERVLETABMCPERSONA/*", "/servletABMCpersona/*" })
public class ServletABMCPersona extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;

    public ServletABMCPersona() {
    	super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				request.getRequestDispatcher("WEB-INF/FormUsuario.jsp?accion="+request.getParameter("fin")).forward(request, response);;
			}
			
			
		} catch (Exception ex) {
		
			this.error(request, response, ex);
		}
		
	}


	private void baja(HttpServletRequest request, HttpServletResponse response) {
		Persona per=new Persona();
		per.setDni(request.getParameter("dni"));
		if(Campo.Valida(per.getDni(), Campo.tipo.DNI)){
			try{
				CtrlPersonaLogic ctrl =new CtrlPersonaLogic();
				ctrl.delete(per);
				hacerInforme(request, response, TipoInforme.EXITO , "Usuario", "Persona eliminada correctamente","ServletListaUsuarios");			

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
			Persona per=new Persona();
			//per.setId(Integer.parseInt(request.getParameter("id")));
			per.setUsuario(request.getParameter("usuario"));
			per.setContrasenia(request.getParameter("contrasenia"));
			per.setCategoria(new CtrlCategoriaLogic().getOne(request.getParameter("categoria")));
			per.setApellido(request.getParameter("apellido"));		
			per.setNombre(request.getParameter("nombre"));
			per.setDni(request.getParameter("dni"));
			per.setHabilitado(request.getParameter("habilitado")==null?false:true);
			per.setEmail(request.getParameter("email"));
			
			
				
			if(this.validaCampos(per.getDni(), per.getEmail())){
				CtrlPersonaLogic ctrl= new CtrlPersonaLogic();
				ctrl.update(per);
				hacerInforme(request, response, TipoInforme.EXITO , "Usuario", "Datos de persona actualizados correctamente","ServletListaUsuarios");			

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
			Persona per=new Persona();
			per.setUsuario(request.getParameter("usuario"));
			per.setContrasenia(request.getParameter("contrasenia"));
			per.setCategoria(new CtrlCategoriaLogic().getOne(request.getParameter("categoria")));
			per.setApellido(request.getParameter("apellido"));		
			per.setNombre(request.getParameter("nombre"));
			per.setDni(request.getParameter("dni"));
			per.setHabilitado(request.getParameter("habilitado")==null?false:true);
			per.setEmail(request.getParameter("email"));
			
			
			
			if(this.validaCampos(per.getDni(), per.getEmail())){
				CtrlPersonaLogic ctrl= new CtrlPersonaLogic();
				ctrl.add(per);
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
