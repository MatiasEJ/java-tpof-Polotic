/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades.personas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import logica.entidades.reserva.Reserva;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author keta
 */
@Entity
public class Cliente extends BaseEntity {

	@Basic
	String profesion;
	@OneToMany(mappedBy = "cliente")
	List<Reserva> listaReservas;

	public Cliente() {
		listaReservas = new ArrayList<>();
	}


	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Cliente(String profesion) {
		this.profesion = profesion;
	}

	public Cliente(String profesion, String nombre, String apellido, String dni, String direccion, Date fechaNacimiento) {
		super(nombre, apellido, dni, direccion, fechaNacimiento);
		this.profesion = profesion;
	}


	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}





}
