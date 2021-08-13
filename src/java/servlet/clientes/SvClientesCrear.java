/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.clientes;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.controladoras.ControladoraCliente;
import logica.util.DatosPersona;
import logica.util.Paginas;
import logica.util.Utilidades;

/**
 *
 * @author keta
 */
@WebServlet(name = "svClientes", urlPatterns = {"/svClientes"})
public class SvClientesCrear extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(Paginas.ALTA_CLIENTES);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Parametros y conversiones
		String nombre = request.getParameter(DatosPersona.NOMBRE);
		String apellido = request.getParameter(DatosPersona.APELLIDO);
		String dni = request.getParameter(DatosPersona.DNI);
		String direccion = request.getParameter(DatosPersona.DIRECCION);
		String profesion = request.getParameter(DatosPersona.PROFESION);
		String fn = request.getParameter(DatosPersona.FECHA_NAC);
		Date fechaNac = Utilidades.convertirFecha(fn);

		//Session
		request.getSession().setAttribute(DatosPersona.NOMBRE, nombre);
		request.getSession().setAttribute(DatosPersona.APELLIDO, apellido);
		request.getSession().setAttribute(DatosPersona.DNI, dni);
		request.getSession().setAttribute(DatosPersona.DIRECCION, direccion);
		request.getSession().setAttribute(DatosPersona.PROFESION, profesion);
		request.getSession().setAttribute(DatosPersona.FECHA_NAC, fn);

		//Save
		ControladoraCliente controladora = new ControladoraCliente();
		controladora.crearCliente(nombre, apellido, dni, direccion, profesion, fechaNac);
		
		response.sendRedirect(Paginas.ALTA_CLIENTES);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	@Override
	public void init() throws ServletException {
//		ControladoraCliente controladora = new ControladoraCliente();
		
//		controladora.crearCliente("Juan", "juanito", "30303030", "calle falsa 123", "hacker", new Date());
	}




	
}
