/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.reservas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraCliente;
import logica.util.Paginas;
import logica.controladoras.ControladoraReservas;
import logica.modelos.personas.Cliente;
import logica.modelos.reserva.Reserva;
import logica.util.DatosReservas;

/**
 *
 * @author keta
 */
@WebServlet(name = "SvReservasConsultas", urlPatterns = {"/SvReservasConsultas"})
public class SvReservasConsultas extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraReservas controladora = new ControladoraReservas();
		ControladoraCliente controaldoraCliente = new ControladoraCliente();
		List<Cliente> listaClientes = controaldoraCliente.findAllClientes();
		List<Reserva> listaReservas = controladora.findAllReserva();
		HttpSession mysession = request.getSession();
		if (listaReservas != null ) {
			
			mysession.setAttribute("listaClientes", listaClientes);
			mysession.setAttribute("listaReservas", listaReservas);
			response.sendRedirect(Paginas.LISTA_RESERVAS);
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
