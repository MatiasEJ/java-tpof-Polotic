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
import logica.util.Paginas;
import logica.controladoras.ControladoraEmpleado;
import logica.controladoras.ControladoraHabitacion;
import logica.controladoras.ControladoraReservas;
import logica.modelos.habitaciones.Habitacion;
import logica.modelos.personas.Cliente;
import logica.modelos.personas.Empleado;
import logica.modelos.reserva.Reserva;
import logica.util.DatosReservas;
import logica.util.Utilidades;

@WebServlet(name = "SvReservasCrear", urlPatterns = {"/SvReservasCrear"})
public class SvReservasCrear extends HttpServlet {

	ControladoraReservas controladoraReserva = new ControladoraReservas();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControladoraHabitacion ch = new ControladoraHabitacion();
		ControladoraReservas cr = new ControladoraReservas();
		HttpSession mySession = request.getSession();
		mySession.setAttribute("listaReservas", cr.findAllReserva());
		mySession.setAttribute("listaHabitacion", ch.findAllHabitacion());
		response.sendRedirect(Paginas.ALTA_RESERVAS);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fechaIn = request.getParameter(DatosReservas.FECHA_IN);
		String fechaOut = request.getParameter(DatosReservas.FECHA_OUT);
		int cantHuespedesReserva = Integer.parseInt(request.getParameter(DatosReservas.CANT_HUESPEDES));
		int cantDiasReserva = Utilidades.cantidadDiasEstadia(fechaIn, fechaOut);
		int idHabitacion = Integer.parseInt(request.getParameter("idHabitacion"));
		String dniCliente = request.getParameter("dniCliente");
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		ControladoraHabitacion controladoraHabitaciones = new ControladoraHabitacion();
		ControladoraCliente controladoraCliente = new ControladoraCliente();
		Cliente cliente;
		if(dniCliente != null && !dniCliente.isEmpty()){
		 cliente = controladoraCliente.findClienteByDni(dniCliente);
		}else{
			cliente= controladoraCliente.findClienteById(idCliente);
		}
		Habitacion habitacion = controladoraHabitaciones.findHabitacionById(idHabitacion);

		boolean authCantHuespedes = controladoraHabitaciones.cantidadCorrectaHuespedes(cantHuespedesReserva, idHabitacion);
		double monto = habitacion.calcularMontoEstadia(cantDiasReserva);
		boolean authFechaReserva = controladoraReserva.isFechaReservaDisponible(fechaIn, fechaOut);
		String error = "";

		if (!authFechaReserva){
			error = error.concat("Error fecha reserva. ");
		}
		if(!authCantHuespedes){
			error = error.concat("Cantidad erronea de huespedes. ");
		}
		
		if(authFechaReserva && authCantHuespedes){

			if (cliente != null) {
				request.getSession().setAttribute("objCliente", cliente);
				request.getSession().setAttribute("objHabitacion", habitacion);
				request.getSession().setAttribute(DatosReservas.FECHA_IN, fechaIn);
				request.getSession().setAttribute(DatosReservas.FECHA_OUT, fechaOut);
				request.getSession().setAttribute("cantDiasReserva", cantDiasReserva);
				request.getSession().setAttribute(DatosReservas.DIAS_RESERVA, cantDiasReserva);
				request.getSession().setAttribute(DatosReservas.CANT_HUESPEDES, cantHuespedesReserva);
				request.getSession().setAttribute(DatosReservas.MONTO, monto);
				request.getSession().setAttribute("tipoHab", habitacion.getTipoHabitacion().toString());
				request.getSession().setAttribute("temaHab", habitacion.getTematica().toString());
				request.getSession().setAttribute("listaHabitaciones", controladoraHabitaciones.findAllHabitacion());
				

				response.sendRedirect(Paginas.CONFIRMAR_RESERVA);
			} else {
				response.sendRedirect(Paginas.ALTA_CLIENTES);
			}
		} else {
			error = error.concat("\n Error reserva, vuelva a intentarlo.");
			request.getSession().setAttribute("error", error);
			response.sendRedirect(Paginas.ALTA_RESERVAS);
		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
