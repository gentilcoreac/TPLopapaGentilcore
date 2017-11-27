package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HttpServletConFunciones
 */
@WebServlet("/HttpServletConFunciones")
public class HttpServletConFunciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public static enum TipoInforme{  ERROR("error"),INFO("info"),EXITO("exito");
									private final String texto;
									private TipoInforme(final String texto){this.texto=texto;}
									@Override
									public String toString(){return texto;}}
	
	
    public HttpServletConFunciones() {
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
		doGet(request, response);
	}

	
	protected void error(HttpServletRequest request,HttpServletResponse response){
		response.setStatus(404);
	}

	protected void error(HttpServletRequest request, HttpServletResponse response,Exception ex) {
		
		this.hacerInforme(request, response, TipoInforme.ERROR, "Error", ex.getMessage());
	
	}
	
	protected void hacerInforme(HttpServletRequest request, HttpServletResponse response,TipoInforme tipo,String titulo,String mensaje){
		request.setAttribute("Titulo", titulo);
		request.setAttribute("Mensaje", mensaje);
		try{
			request.getRequestDispatcher("WEB-INF/Informes.jsp?tipo="+tipo).forward(request, response);				
		} catch (Exception e) {
			response.setStatus(500);
		} 
	}
}
