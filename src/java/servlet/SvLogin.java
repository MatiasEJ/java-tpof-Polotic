/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.util.Paginas;
import logica.controladoras.ControladoraEmpleado;
import logica.modelos.personas.Empleado;
import logica.util.DatosPersona;

/**
 *
 * @author keta
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

	ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Paginas.LOGIN);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombreUsuario = request.getParameter(DatosPersona.NOMBRE_USUARIO);
		String password =  request.getParameter(DatosPersona.PASSWORD);


		boolean empleadoAuth = controladoraEmpleado.checkEmpleadoCredentials(nombreUsuario, password);
		Empleado empleado = controladoraEmpleado.findEmpleadoByUsername(nombreUsuario);
		if (empleado != null) {
			request.getSession().setAttribute("empleado", empleado);
		}
		if (empleadoAuth ) {
			request.getSession().setAttribute(DatosPersona.NOMBRE_USUARIO, nombreUsuario);
			request.getSession().setAttribute(DatosPersona.PASSWORD, password);
			response.sendRedirect(Paginas.HOME);
		}else{
			request.getSession().removeAttribute(DatosPersona.NOMBRE_USUARIO);
			response.sendRedirect(Paginas.LOGIN);
		} 
			
	}


	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
