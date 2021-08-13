/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades.personas;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import logica.entidades.reserva.Reserva;

/**
 *
 * @author keta
 */
@Entity
public class Empleado extends BaseEntity {

	@Basic
	String Cargo;

	@OneToOne(cascade = CascadeType.REMOVE)
	Usuario usuario;

	@OneToMany(mappedBy = "empleado")
	List<Reserva> listaReservasEmpleado;

	public Empleado() {
	}

	public Empleado(String Cargo, Usuario usuario) {
		this.Cargo = Cargo;
		this.usuario = usuario;
	}

	public Empleado(Usuario usuario, String nombre) {
		super(nombre);
		this.usuario = usuario;
	}

	public List<Reserva> getListaReservasEmpleado() {
		return listaReservasEmpleado;
	}
	


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empleado(String Cargo) {
		this.Cargo = Cargo;
	}

	public Empleado(String Cargo, String nombre, String apellido, String dni, String direccion, Date fechaNacimiento) {
		super(nombre, apellido, dni, direccion, fechaNacimiento);
		this.Cargo = Cargo;
	}

	public Usuario getUsuario() {
		return usuario;
	}




	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String Cargo) {
		this.Cargo = Cargo;
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
