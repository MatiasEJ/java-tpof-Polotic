/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidades.habitaciones.Habitacion;
import logica.entidades.personas.Empleado;
import logica.entidades.reserva.Reserva;
import logica.util.Utilidades;
import persistencia.ReservaPersistencia;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author keta
 */
public class ControladoraReservas {

	ReservaPersistencia reservaPersistencia = new ReservaPersistencia();

	public void crearReserva(
			String checkIn,
			String checkout,
			int cantHuespedes,
			Habitacion habitacion, Empleado empleado
	) {
		Reserva reserva = new Reserva();
		Date checkInRes = Utilidades.convertirFecha(checkIn);
		Date checkOutRes = Utilidades.convertirFecha(checkout);
		reserva.setEmpleado(empleado);
		reserva.setFechaCheckin(checkInRes);
		reserva.setFechaCheckout(checkOutRes);
		reserva.setFechaReserva(new Date());
		reserva.setCantHuespedes(cantHuespedes);
		reserva.setMontoEstadia(calcularMonto(habitacion, checkIn, checkout));
		reserva.setHabitacion(habitacion);
		reserva.setCantDiasReserva(Utilidades.cantidadDiasEstadia(checkIn, checkout));

		reservaPersistencia.crearReserva(reserva);

	}

	public void crearReserva(Reserva reserva) {
		reservaPersistencia.crearReserva(reserva);
	}

	public List<Reserva> findAllReserva() {
		return reservaPersistencia.findAllReserva();
	}

	public boolean isFechaReservaDisponible(String fechaIn, String fechaOut) {
		List<Reserva> listaReservas = reservaPersistencia.findAllReserva();
		if (listaReservas == null || listaReservas.isEmpty()) {
			return true;
		} else {
			System.out.println("fecha in" + fechaIn);
			System.out.println("fecha out" + fechaOut);
			Date fechaCheckIn = Utilidades.convertirFecha(fechaIn);
			Date fechaCheckOut = Utilidades.convertirFecha(fechaOut);
			System.out.println("fechaconv IN " + fechaCheckIn.toString());
			System.out.println("fechaconv out " + fechaCheckOut.toString());
			for (Reserva reserva : reservaPersistencia.findAllReserva()) {
				System.out.println("FECHARESERVA in+ " + reserva.getFechaCheckin());
				System.out.println("FECHARESERVA out+ " + reserva.getFechaCheckout());
				boolean puedeReservar = Utilidades.overlap(fechaCheckIn, fechaCheckOut, reserva.getFechaCheckin(), reserva.getFechaCheckout());
				if (puedeReservar) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean isFechaDisponible(Date fecha) {
		List<Reserva> listaReservas = findAllReserva();
		for (Reserva reserva : listaReservas) {
			Date fechaIn = reserva.getFechaCheckin();
			Date fechaOut = reserva.getFechaCheckout();
			if (Utilidades.isDateBetween(fechaIn, fechaOut, fecha)) {
				return true;
			}

		}
		return false;
	}

	public void borrarReservaById(int id) {
		try {
			reservaPersistencia.borrarReservaById(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(ControladoraReservas.class
					.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Reserva> findReservasByDate(Date fechaReserva) {
		List<Reserva> listaReservas = new ArrayList();
		if (fechaReserva != null) {
			for (Reserva res : reservaPersistencia.findAllReserva()) {
				if (res.getFechaReserva().compareTo(fechaReserva) == 0) {
					listaReservas.add(res);
				}
			}
			return listaReservas;
		}
		return null;
	}

	private double calcularMonto(Habitacion habitacion, String fechaIn, String fechaOut) {
		int cantDiasReserva = Utilidades.cantidadDiasEstadia(fechaOut, fechaIn);
		return habitacion.calcularMontoEstadia(cantDiasReserva);
	}

	public List<Reserva> findAllReservaByEmpleadoId(int id) {
		int idComp = id;
		List<Reserva> listaReservas = new ArrayList();
		for (Reserva res : reservaPersistencia.findAllReserva()) {
			int idEmp = res.getEmpleado().getId();
			if (idEmp == idComp) {
				listaReservas.add(res);
			}
		}

		return listaReservas;
	}

	public List<Reserva> findAllResevaByClienteId(int id) {
		List<Reserva> listaReservas = reservaPersistencia.findAllReserva();
		List<Reserva> tmp = new ArrayList<>();
		for (Reserva reserva : listaReservas) {
			if(reserva.getCliente().getId() == id){
				tmp.add(reserva);
			}
		}
		return tmp;
	}

	public List<Reserva> findAllReservasClientByDate(int idCliente, Date in, Date out) {
		List<Reserva> resTemp = new ArrayList<>();
		List<Reserva> listaReservasCliente = findAllResevaByClienteId(idCliente);
		List<Reserva> listaReservas = reservaPersistencia.findAllReserva();

		List<Date> checkIns = new ArrayList<>();
		List<Date> checkOuts = new ArrayList<>();
		for (Reserva reserva : listaReservas) {
			if (reserva.getCliente().getId() == idCliente) {
				checkIns.add(reserva.getFechaCheckin());
				checkOuts.add(reserva.getFechaCheckout());
			}
		}
		if(checkIns.isEmpty() || checkOuts.isEmpty()){
			return null;
		}
		System.out.println("ins");
		for (Date ixn : checkIns) {
			System.out.println(Utilidades.dateAString(ixn));
		}
		System.out.println("outs");
		for (Date ou : checkOuts) {
			System.out.println(Utilidades.dateAString(ou));
		}
		Date min = Collections.min(checkIns);
		Date max = Collections.max(checkOuts);
		System.out.println("MIN " + Utilidades.dateAString(min));
		System.out.println("MAX " + Utilidades.dateAString(max));

		System.out.println("lias" + listaReservasCliente.isEmpty());
		for (Reserva res : listaReservasCliente) {
			System.out.println("res-" + res.getFechaReserva());
			resTemp.add(res);
		}
		for (Reserva re : resTemp) {
			System.out.println("res-" + re.getFechaReserva());
		}

		return resTemp;
	}

	public Reserva findReservaById(int idReserva) {
		return reservaPersistencia.findReservaById(idReserva);
	}

	public void modificarReserva(Reserva reservaTmb) {
		reservaPersistencia.modifcarReserva(reservaTmb);
	}

	public Reserva findReservaByClienteId(int idCliente) {
		for(Reserva reserva : reservaPersistencia.findAllReserva()){
			if(reserva.getCliente().getId() == idCliente){
				return reserva;	
			}	
		}
		return null;
	}

}
