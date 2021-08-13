/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.entidades.personas.Cliente;
import logica.entidades.reserva.Reserva;
import persistencia.ClientePersistencia;

/**
 *
 * @author keta
 */
public class ControladoraCliente {
	ClientePersistencia persistenciaCliente = new ClientePersistencia();
	
	public void crearCliente(String nombre, String apellido, String dni,String direccion, String profesion, Date fechaNacimiento){
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(nombre);
		nuevoCliente.setApellido(apellido);
		nuevoCliente.setDni(dni);
		nuevoCliente.setDireccion(direccion);
		nuevoCliente.setProfesion(profesion);
		nuevoCliente.setFechaNacimiento(fechaNacimiento);
		persistenciaCliente.crearCliente(nuevoCliente);
	}

	public List<Cliente> findAllClientes(){
	 return persistenciaCliente.findAllClientes();
	}

	public boolean isClienteByDni(String dniCliente) {
		for(Cliente cliente: persistenciaCliente.findAllClientes()){
			if (cliente.getDni().equals(dniCliente)) {
			return true;	
			}
			
		}
		return false;
	}

	public Cliente findClienteByDni(String dniCliente) {
		for(Cliente cliente: persistenciaCliente.findAllClientes()){
			if (cliente.getDni().equals(dniCliente)) {
			return cliente;	
			}
			
		}
		return null;
	}

	public Cliente findClienteById(int idCliente) {
		return persistenciaCliente.findClienteById(idCliente);
	}


	public void borrarClienteById(int idCliente) {
		persistenciaCliente.borrarClienteById(idCliente);
	}

	public void modificarCliente(Cliente cliente) {
		persistenciaCliente.modificarCliente(cliente);
	}




	
}
