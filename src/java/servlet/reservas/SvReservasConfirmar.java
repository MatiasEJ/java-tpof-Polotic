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
import logica.modelos.habitaciones.Habitacion;
import logica.modelos.personas.Cliente;
import logica.modelos.personas.Empleado;
import logica.modelos.reserva.Reserva;
import logica.util.DatosPersona;
import logica.util.DatosReservas;
import logica.util.Paginas;
import logica.util.Utilidades;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@WebServlet(name = "SvReservasConfirmar", urlPatterns = {"/SvReservasConfirmar"})
public class SvReservasConfirmar extends HttpServlet {

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
		ControladoraReservas controladoraReserva = new ControladoraReservas();
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		ControladoraHabitacion controladoraHabitacion = new ControladoraHabitacion();
		ControladoraEmpleado controladoraEmpleado = new ControladoraEmpleado();

		Date fechaIn= Utilidades.convertirFecha(request.getParameter(DatosReservas.FECHA_IN));
		Date fechaOut= Utilidades.convertirFecha(request.getParameter(DatosReservas.FECHA_OUT));
		int cantHuespedesReserva = Integer.parseInt(request.getParameter(DatosReservas.CANT_HUESPEDES));
		int cantDiasReserva = Utilidades.cantidadDiasEstadia(request.getParameter(DatosReservas.FECHA_IN),request.getParameter(DatosReservas.FECHA_OUT) );
		//IDs
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		int idHabitacion = Integer.parseInt(request.getParameter("idHabitacion"));
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		Cliente cliente = controladoraCliente.findClienteById(idCliente);
		Habitacion habitacion = controladoraHabitacion.findHabitacionById(idHabitacion);
		double monto = habitacion.calcularMontoEstadia(cantDiasReserva);
		habitacion.setFechaCheckin(fechaIn);
		habitacion.setFechaReserva(fechaOut);
		Empleado empleado = controladoraEmpleado.findEmpleadoById(idEmpleado);

		Reserva reserva = new Reserva();
		reserva.setCantDiasReserva(cantDiasReserva);
		reserva.setCantHuespedes(cantHuespedesReserva);
		reserva.setEmpleado(empleado);
		reserva.setFechaCheckin(fechaIn);
		reserva.setFechaCheckout(fechaOut);
		reserva.setFechaReserva(new Date());
		reserva.setMontoEstadia(monto);

		habitacion.setIsReservada(true);
		controladoraHabitacion.modificarHabitacion(habitacion);
		reserva.setHabitacion(habitacion);
		reserva.setCliente(cliente);
		controladoraReserva.crearReserva(reserva);
		HttpSession mysession = request.getSession();
		
		mysession.setAttribute("listaReservas", controladoraReserva.findAllReserva());

		response.sendRedirect(Paginas.LISTA_RESERVAS);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Params
		int id = Integer.parseInt(request.getParameter("idReserva"));
		//acciones
		ControladoraReservas controladoraReservas = new ControladoraReservas();
		Reserva reserva = controladoraReservas.findReservaById(id);
		//enviar Atributos
		HttpSession mySession = request.getSession();
		mySession.setAttribute("objReserva", reserva);

		//redirect	
		response.sendRedirect(Paginas.CONFIRMAR_RESERVA);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
