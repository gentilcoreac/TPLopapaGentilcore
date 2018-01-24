package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entities.Persona;
import business.entities.TipoDeElemento;
import business.logic.CtrlReservaLogic;
import business.logic.CtrlTipoDeElementoLogic;


@WebServlet({"/ServletListaTiposDeElementos","/servletlistatiposdeelementos"})
public class ServletListaTiposDeElementos extends HttpServletConFunciones {
	private static final long serialVersionUID = 1L;
       

    public ServletListaTiposDeElementos() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			CtrlTipoDeElementoLogic ctrl=new CtrlTipoDeElementoLogic();
			ArrayList<TipoDeElemento> tipos=new ArrayList<TipoDeElemento>();
			tipos=ctrl.getAll();
			request.setAttribute("listaTipos", tipos);
			int[] respendientes=new int[tipos.size()];
			CtrlReservaLogic ctrlres=new CtrlReservaLogic();
			for(int i=0;i<tipos.size();++i){
				respendientes[i]=ctrlres.getNumResPen((Persona)request.getSession().getAttribute("user"), tipos.get(i));
			}
			request.setAttribute("respendientes", respendientes);
			request.getRequestDispatcher("WEB-INF/ListadoTiposDeElementos.jsp").forward(request, response);
		}
		catch(Exception ex){
			error(request,response,ex);
		}
	}

}
