/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.clientes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.controladoras.ControladoraCliente;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvClientesBaja", urlPatterns = {"/SvClientesBaja"})
public class SvClientesBaja extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//params
		int idCliente =Integer.parseInt(request.getParameter("idCliente"));
		//acciones
		ControladoraCliente controladora = new ControladoraCliente();
//		controladora.bajaClienteById(idCliente);
		controladora.borrarClienteById(idCliente);
		//enviar atributos
		request.getSession().setAttribute("listaClientes", controladora.findAllClientes());

		response.sendRedirect(Paginas.LISTA_CLIENTES);
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
