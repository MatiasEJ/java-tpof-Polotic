/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.empleados;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.util.DatosPersona;
import logica.util.Paginas;
import logica.util.Utilidades;
import logica.controladoras.ControladoraEmpleado;
import logica.controladoras.ControladoraUsuario;
import logica.modelos.personas.Usuario;

/**
 *
 * @author keta
 */
@WebServlet(name = "SvEmpleadosCrear", urlPatterns = {"/SvEmpleadosCrear"})
public class SvEmpleadosCrear extends HttpServlet {

	ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
	ControladoraUsuario controladoraUsuario = new ControladoraUsuario();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Paginas.ALTA_EMPLEADOS);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter(DatosPersona.NOMBRE);
		String apellido = request.getParameter(DatosPersona.APELLIDO);
		String dni = request.getParameter(DatosPersona.DNI);
		String direccion = request.getParameter(DatosPersona.DIRECCION);
		String cargo = request.getParameter(DatosPersona.CARGO);
		String fechNacSt = request.getParameter(DatosPersona.FECHA_NAC);
		Date fechaNac = Utilidades.convertirFecha(fechNacSt);
		String nombreUsuario = request.getParameter(DatosPersona.NOMBRE_USUARIO);
		String password = request.getParameter(DatosPersona.PASSWORD);


		//Save
		Usuario usuario = controladoraUsuario.crearUsuario(nombreUsuario, password);

		controladoraEmpleado.crearEmpleado(nombre, apellido, dni, direccion, cargo, fechaNac, usuario);

		//enviar datos
		request.getSession().setAttribute("listaEmpleados", controladoraEmpleado.findAllEmpleado());
		//Redirect		
		response.sendRedirect(Paginas.LISTA_EMPLEADOS);

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
