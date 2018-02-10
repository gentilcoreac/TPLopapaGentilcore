package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/HttpServletConFunciones")
public class HttpServletConFunciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Logger logger;
    
	public static enum TipoInforme{  ERROR("error"),INFO("info"),EXITO("exito");
									private final String texto;
									private TipoInforme(final String texto){this.texto=texto;}
									@Override
									public String toString(){return texto;}}
	
	
    public HttpServletConFunciones() {
        super();
		logger = LogManager.getLogger(getClass());
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

	
	protected void error(HttpServletRequest request,HttpServletResponse response){
		logger.log(Level.ERROR,"Server Status 404");
		response.setStatus(404);
	}

	protected void error(HttpServletRequest request, HttpServletResponse response,Exception ex) {
		
		logger.log(Level.ERROR,ex.getMessage());
		this.hacerInforme(request, response, TipoInforme.ERROR, "Error", ex.getMessage());
	
	}
	
	protected void hacerInforme(HttpServletRequest request, HttpServletResponse response,TipoInforme tipo,String titulo,String mensaje){
		this.hacerInforme(request, response, tipo, titulo, mensaje, null);
	}
	
	protected void hacerInforme(HttpServletRequest request, HttpServletResponse response,TipoInforme tipo,String titulo,String mensaje,String urlvolver){
		request.setAttribute("Titulo", titulo);
		request.setAttribute("Mensaje", mensaje);
		request.setAttribute("urlvolver", urlvolver);
		try{
			request.getRequestDispatcher("WEB-INF/Informes.jsp?tipo="+tipo).forward(request, response);				
		} catch (Exception e) {
			logger.log(Level.FATAL,"Error de direccionamiento-Server Status 500");
			response.setStatus(500);
		} 
	}
}
