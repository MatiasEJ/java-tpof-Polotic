/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.reservas;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoras.ControladoraCliente;
import logica.controladoras.ControladoraEmpleado;
import logica.controladoras.ControladoraHabitacion;
import logica.controladoras.ControladoraReservas;
import logica.entidades.habitaciones.Habitacion;
import logica.entidades.personas.Cliente;
import logica.entidades.personas.Empleado;
import logica.entidades.reserva.Reserva;
import logica.util.DatosReservas;
import logica.util.Paginas;
import logica.util.Utilidades;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvReservasMod", urlPatterns = {"/SvReservasMod"})
public class SvReservasMod extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraReservas controladoraReservas = new ControladoraReservas();
		Date fechaIn= Utilidades.convertirFecha(request.getParameter(DatosReservas.FECHA_IN));
		Date fechaOut= Utilidades.convertirFecha(request.getParameter(DatosReservas.FECHA_OUT));
		int cantHuespedesReserva = Integer.parseInt(request.getParameter(DatosReservas.CANT_HUESPEDES));
		int cantDiasReserva = Utilidades.cantidadDiasEstadia(request.getParameter(DatosReservas.FECHA_IN),request.getParameter(DatosReservas.FECHA_OUT) );
		//IDs
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		int idHabitacion = Integer.parseInt(request.getParameter("idHabitacion"));
		
		int oldHab = Integer.parseInt(request.getParameter("oldHabitacion"));
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		int idReserva = Integer.parseInt(request.getParameter("idReserva"));
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		Cliente cliente = controladoraCliente.findClienteById(idCliente);
		ControladoraHabitacion controladoraHabitacion = new ControladoraHabitacion();		

		Habitacion habitacion = controladoraHabitacion.findHabitacionById(idHabitacion);
		Habitacion oldHabitacion = controladoraHabitacion.findHabitacionById(oldHab);
		oldHabitacion.setIsReservada(false);
		controladoraHabitacion.modificarHabitacion(oldHabitacion);
		habitacion.setIsReservada(true);
		controladoraHabitacion.modificarHabitacion(habitacion);
		
		ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();
		Empleado empleado = controladoraEmpleado.findEmpleadoById(idEmpleado);

		Reserva reserva = controladoraReservas.findReservaById(idReserva);
		reserva.setFechaCheckin(fechaIn);
		reserva.setFechaCheckout(fechaOut);
		reserva.setCantHuespedes(cantHuespedesReserva);
		reserva.setCantDiasReserva(cantDiasReserva);
		reserva.setEmpleado(empleado);
		reserva.setHabitacion(habitacion);
		reserva.setCliente(cliente);

		controladoraReservas.modificarReserva(reserva);
		
		request.getSession().setAttribute("listaReservas", controladoraReservas.findAllReserva());
		response.sendRedirect(Paginas.LISTA_RESERVAS);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Params
		int id = Integer.parseInt(request.getParameter("idReserva"));
		//acciones
		ControladoraReservas controladoraReserva = new ControladoraReservas();
		Reserva reserva = controladoraReserva.findReservaById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute("objReserva", reserva);

		//redirect	
		response.sendRedirect(Paginas.MOD_RESERVA);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
