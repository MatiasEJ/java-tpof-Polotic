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
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraCliente;
import logica.modelos.personas.Cliente;
import logica.util.DatosPersona;
import logica.util.Paginas;
import logica.util.Utilidades;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvClientesMod", urlPatterns = {"/SvClientesMod"})
public class SvClientesMod extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Parametros y conversiones
		String nombre = request.getParameter(DatosPersona.NOMBRE);
		String apellido = request.getParameter(DatosPersona.APELLIDO);
		String dni = request.getParameter(DatosPersona.DNI);
		String direccion = request.getParameter(DatosPersona.DIRECCION);
		String profesion = request.getParameter(DatosPersona.PROFESION);
		String fn = request.getParameter(DatosPersona.FECHA_NAC);
		Date fechaNac = Utilidades.convertirFecha(fn);
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		Cliente cliente = controladoraCliente.findClienteById(idCliente);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDireccion(direccion);
		cliente.setDni(dni);
		cliente.setFechaNacimiento(fechaNac);
		cliente.setProfesion(profesion);

		controladoraCliente.modificarCliente(cliente);


		//enviar datos
		request.getSession().setAttribute("listaClientes", controladoraCliente.findAllClientes());

		response.sendRedirect(Paginas.LISTA_CLIENTES);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Params
		int id = Integer.parseInt(request.getParameter(DatosPersona.ID_CLIENTE_PARAM));
		//acciones
		ControladoraCliente cc = new ControladoraCliente();
		Cliente cliente = cc.findClienteById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute("objCliente", cliente);

		//redirect	
		response.sendRedirect(Paginas.MOD_CLIENTES);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
