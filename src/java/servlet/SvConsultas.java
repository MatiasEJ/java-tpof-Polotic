/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.util.Utilidades;
import logica.controladoras.ControladoraReservas;
import logica.entidades.reserva.Reserva;
import logica.util.Paginas;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvConsultas", urlPatterns = {"/SvConsultas"})
public class SvConsultas extends HttpServlet {

	ControladoraReservas controladoraReservas = new ControladoraReservas();
	ControladoraReservas cr = new ControladoraReservas();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("consultas.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String consulta = request.getParameter("consulta");

		if (consulta.equals("consultaReservaFecha")) {
			String fr = request.getParameter("fechaReserva");
			Date fechaReserva = Utilidades.convertirFecha(fr);
			request.getSession().setAttribute("listaReservas", controladoraReservas.findReservasByDate(fechaReserva));
			response.sendRedirect(Paginas.LISTA_RESERVAS);
		} else {
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			if (idCliente == 0) {
				response.sendRedirect(Paginas.LISTA_RESERVAS);
			}
			Date in = Utilidades.convertirFecha(request.getParameter("fechaConsultaIn"));
			Date out = Utilidades.convertirFecha(request.getParameter("fechaConsultaOut"));
			List<Reserva> listaReservas = controladoraReservas.findAllReservasClientByDate(idCliente, in, out);
			if (listaReservas == null) {
				response.sendRedirect(Paginas.LISTA_RESERVAS);

			} else {
				HttpSession mySession = request.getSession();
				mySession.setAttribute("listaReservas", listaReservas);
				response.sendRedirect(Paginas.LISTA_RESERVAS);
			}
		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
