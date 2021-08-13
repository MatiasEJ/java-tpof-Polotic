/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelos.habitaciones;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import logica.modelos.reserva.Reserva;
import logica.util.TematicaHabitacion;
import logica.util.TipoHabitacion;
import logica.util.Utilidades;

/**
 *
 * @author keta
 */
@Entity
public class Habitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Basic
	private String descripcion;
	private boolean isReservada = false;
	private String piso;
	private double precioNoche;
	private int cantMaxHuespedes;
	private int cantHuespedes;
	private TematicaHabitacion tematica;
	private TipoHabitacion tipoHabitacion;
	@Temporal(TemporalType.DATE)
	private Date fechaCheckin;
	@Temporal(TemporalType.DATE)
	private Date fechaCheckout;
	@Temporal(TemporalType.DATE)
	private Date fechaReserva;

	

	public Habitacion() {
		this.fechaCheckin = null;
		this.fechaCheckout = null;
		this.fechaReserva = null;
	}

	public Date getFechaCheckin() {
		return fechaCheckin;
	}

	public String getFechaCheckinString() {
		if (this.fechaCheckin != null) {
			return Utilidades.dateAString(this.fechaCheckin);
		}
		return "-";
	}

	public String getFechaCheckOutString() {
		if (this.fechaCheckout != null) {
		return Utilidades.dateAString(this.fechaCheckout);
		}
		return "-";
	}

	public String getFechaReservaString() {
		if (this.fechaReserva != null) {
		return Utilidades.dateAString(this.fechaReserva);
		}
		return "-";
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

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public void setCantMaxHuespedes(int cantMaxHuespedes) {
		this.cantMaxHuespedes = cantMaxHuespedes;
	}

	public int getId() {
		return id;
	}

	public int getCantHuespedes() {
		return cantHuespedes;
	}

	public void setCantHuespedes(int cantHuespedes) {
		this.cantHuespedes = cantHuespedes;
	}


	public TematicaHabitacion getTematica() {
		return tematica;
	}

	public void setTematica(String tema) {
		for (TematicaHabitacion tematicaHab : TematicaHabitacion.values()) {
			if (tematicaHab.toString().equals(tema)) {
				this.tematica = tematicaHab;
			}
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	public void setTematica(TematicaHabitacion tematica) {
		this.tematica = tematica;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public void setPrecioNochePorTipo(String TipoHab) {
		switch (TipoHab) {
			case "Single":
				this.precioNoche = 10;
				break;
			case "Doble":
				this.precioNoche = 20;
				break;
			case "Triple":
				this.precioNoche = 30;
				break;
			case "Multiple":
				this.precioNoche = 40;
				break;
			default:
		}
	}
	public void setCantMaxPorTipoHab(String tipoHab) {
		String hab = tipoHab.toLowerCase();
		switch (hab) {
			case "single":
				this.cantMaxHuespedes = 1;
				break;
			case "doble":
				this.cantMaxHuespedes = 2;
				break;
			case "triple":
				this.cantMaxHuespedes = 3;
				break;
			case "multiple":
				this.cantMaxHuespedes = 8;
				break;
			default:
				this.cantMaxHuespedes = 0;
				break;
		}
	}

	public int getCantMaxHuespedes() {
		return cantMaxHuespedes;
	}

//	public void setTematica(Tematica tematica) {
//		this.tematica = tematica;
//	}
	public long getId_habitacion() {
		return id;
	}

	public void setId_habitacion(int id_habitacion) {
		this.id = id_habitacion;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public boolean isIsReservada() {
		return isReservada;
	}

	public void setIsReservada(boolean isReservada) {
		this.isReservada = isReservada;
	}

//	public Tematica getTematica() {
//		return tematica;
//	}
	public double calcularMontoEstadia(int dias) {
		return dias * this.getPrecioNoche();
	}

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		for (TipoHabitacion tipoHab : TipoHabitacion.values()) {
			if (tipoHab.toString().equals(tipoHabitacion)) {
				this.tipoHabitacion = tipoHab;
			}
		}
	}
}
