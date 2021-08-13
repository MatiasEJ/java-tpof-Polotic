/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelos.reserva;

import java.io.Serializable;
import logica.modelos.personas.Cliente;
import logica.modelos.habitaciones.Habitacion;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import logica.modelos.personas.Empleado;

/**
 *
 * @author keta
 */
@Entity
public class Reserva implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fechaCheckin;
	@Temporal(TemporalType.DATE)
	private Date fechaCheckout;
	@Temporal(TemporalType.DATE)
	private Date fechaReserva;
	@OneToOne
	private Habitacion habitacion;
	@ManyToOne
	@JoinColumn(name="res_empl")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name="res_clie")
	private Cliente cliente;
	@Basic
	private double montoTotalReserva;
	private int cantHuespedes;
	private int cantDiasReserva;

	public Empleado getEmpleado() {
		return empleado;
	}

	public Reserva() {
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Reserva(Date fechaCheckin, Date fechaCheckout, Date fechaReserva, Habitacion habitacion, int cantHuespedes) {
		this.fechaCheckin = fechaCheckin;
		this.fechaCheckout = fechaCheckout;
		this.fechaReserva = fechaReserva;
		this.habitacion = habitacion;
		this.cantHuespedes = cantHuespedes;
		this.montoTotalReserva= calcularMontoEstadia();
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setCantDiasReserva(int cantDiasReserva) {
		this.cantDiasReserva = cantDiasReserva;
	}

	

	public int getCantDiasReserva() {
		return cantDiasReserva;
	}

	


	

	

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public void setMontoTotalReserva(double montoTotalReserva) {
		this.montoTotalReserva = montoTotalReserva;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public double getMontoTotalReserva() {
		return montoTotalReserva;
	}

	// <editor-fold defaultstate="collapsed" desc="getters and settters">
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCheckin() {
		return fechaCheckin;
	}

	public void setFechaCheckin(Date fechaCheckin) {
		this.fechaCheckin = fechaCheckin;
	}

	public Date getFechaCheckout() {
		return fechaCheckout;
	}

	public void setFechaCheckout(Date fechaCheckout) {
		this.fechaCheckout = fechaCheckout;
	}


	public double getMontoEstadia() {
		return montoTotalReserva;
	}

	public void setMontoEstadia(double montoEstadia) {
		this.montoTotalReserva = montoEstadia;
	}

	public int getCantHuespedes() {
		return cantHuespedes;
	}

	public void setCantHuespedes(int cantHuespedes) {
		this.cantHuespedes = cantHuespedes;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
// </editor-fold>

	public boolean validacionCantHuespedes(int cantHuespedes) {
		return cantHuespedes <= this.habitacion.getCantMaxHuespedes();
	}

	private double calcularMontoEstadia() {
		return this.cantDiasReserva * this.habitacion.getPrecioNoche();
	}

	private int cantidadDiasEstadia(Date fechaIn, Date fechaOut) {
		long diffInMillies = Math.abs(fechaOut.getTime() - fechaIn.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return Long.signum(diff);
	}


	
}

