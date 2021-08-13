/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@Entity
public class Empleado extends Persona implements Serializable {

	List<Reserva> listaReservas;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	Usuario usuario;

	public Empleado() {
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
