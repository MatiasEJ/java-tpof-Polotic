/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.clientes;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.util.Paginas;
import logica.controladoras.ControladoraCliente;
import logica.entidades.personas.Cliente;

/**
 *
 * @author keta
 */
@WebServlet(name = "svClientesConsultas", urlPatterns = {"/svClientesConsultas"})
public class SvClientesConsultas extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraCliente controladora = new ControladoraCliente();
		List<Cliente> listaClientes = controladora.findAllClientes();
		HttpSession mySession = request.getSession();
		if (listaClientes != null) {
			mySession.setAttribute("listaClientes", listaClientes);
			response.sendRedirect(Paginas.LISTA_CLIENTES);
		} 

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
