/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Matias Ezequiel Juncos.
 */
@Entity
 public class Usuario implements Serializable {
	@Id
	long id;

	String nombreUsuario;
	String contraseña;
	
	@OneToOne
	Empleado empleado;

	public Usuario() {
	}

	public Usuario(String nombre, String contraseña) {
		this.nombreUsuario = nombre;
		this.contraseña = contraseña;
	}
	

	

	
	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public Empleado getEmpleado() {
		return empleado;
	}
	
}
