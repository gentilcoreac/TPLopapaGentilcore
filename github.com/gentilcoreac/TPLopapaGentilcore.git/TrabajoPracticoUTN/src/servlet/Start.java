package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.entities.Persona;
import business.logic.CtrlPersonaLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;



@WebServlet({"/Start","/start"})
public class Start extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
	

   

    public Start() {
    	logger = LogManager.getLogger(getClass());
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		try{
			if(request.getParameter("username")!=null){
				String user=request.getParameter("username");
				String pass=request.getParameter("password");
				
				CtrlPersonaLogic ctrl=new CtrlPersonaLogic();
				Persona usu=ctrl.getLoggedUser(user, pass);
				if(usu!=null){
					if(usu.isHabilitado()){
						request.getSession().setAttribute("user", usu);	
						logger.log(Level.INFO,"Log in - Cat:"+usu.getCategoria().getDescripcion()+" Usr:"+usu.getUsuario()+" DNI:"+usu.getDni());
						request.getRequestDispatcher("WEB-INF/Inicio.jsp").forward(request, response);
					}else{
						 logger.log(Level.INFO,"Usuario inhabilitado - User:"+user+" Password:"+pass);		
						 request.setAttribute("loginError", "usuarioinhabilitado");
						 this.hacerInforme(request, response, TipoInforme.INFO, "Info", "El usuario no se halla habilitado para ingresar al sistema");
						 }
				}
				else{
					logger.log(Level.INFO,"Intento de logueo fallido - Usr:"+user+" Pass:"+pass);					
					request.setAttribute("loginError", "usuarionulo");
					this.hacerInforme(request, response, TipoInforme.INFO, "Info", "Usuario no encontrado");
					}
			}
		else{
			request.getRequestDispatcher("WEB-INF/Inicio.jsp").forward(request, response);

			}
		}
		catch(Exception ex){
			request.setAttribute("loginError", "usuarioinhabilitado");
			this.error(request, response, ex);	
		}

	}
	
	
}
