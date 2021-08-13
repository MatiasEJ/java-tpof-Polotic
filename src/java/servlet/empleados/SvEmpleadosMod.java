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
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraEmpleado;
import logica.controladoras.ControladoraUsuario;
import logica.entidades.personas.Empleado;
import logica.entidades.personas.Usuario;
import logica.util.DatosPersona;
import logica.util.Paginas;
import logica.util.Utilidades;

@WebServlet(name = "SvEmpleadosMod", urlPatterns = {"/SvEmpleadosMod"})
public class SvEmpleadosMod extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		String nombre = request.getParameter(DatosPersona.NOMBRE);
		String apellido = request.getParameter(DatosPersona.APELLIDO);
		String dni = request.getParameter(DatosPersona.DNI);
		String direccion = request.getParameter(DatosPersona.DIRECCION);
		String cargo = request.getParameter(DatosPersona.CARGO);
		String fechNacSt = request.getParameter(DatosPersona.FECHA_NAC);
		System.out.println("fecha "+fechNacSt);
		Date fechaNac = Utilidades.deStringToDateInput(fechNacSt);
		String nombreUsuario = request.getParameter(DatosPersona.NOMBRE_USUARIO);
		String password = request.getParameter(DatosPersona.PASSWORD);
		int idEmpleado = Integer.parseInt(request.getParameter(DatosPersona.ID_EMPLEADO_PARAM));

		//Save
		Empleado empleado = controladoraEmpleado.findEmpleadoById(idEmpleado);
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDireccion(direccion);
		empleado.setDni(dni);
		empleado.setCargo(cargo);
		empleado.setFechaNacimiento(fechaNac);

		Usuario usuario = controladoraUsuario.findUsuarioById(empleado.getUsuario().getId());
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setPassword(password);
		empleado.setUsuario(usuario);

		controladoraUsuario.modificarUsuario(usuario);
		controladoraEmpleado.modicarEmpleado(empleado);

		//enviar datos
		request.getSession().setAttribute("listaEmpleados", controladoraEmpleado.findAllEmpleado());

		//Redirect		
		response.sendRedirect(Paginas.LISTA_EMPLEADOS);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Params
		int id = Integer.parseInt(request.getParameter(DatosPersona.ID_EMPLEADO_PARAM));
		//acciones
		ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
		Empleado empleado = controladoraEmpleado.findEmpleadoById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute(DatosPersona.PARAM_OBJ_EMPLEADO, empleado);

		//redirect	
		response.sendRedirect(Paginas.MOD_EMPLEADOS);

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
