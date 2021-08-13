/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.modelos.personas.Cliente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author keta
 */
public class ClientePersistencia {
	ClienteJpaController clienteController = new ClienteJpaController();
	
	
	public void crearCliente(Cliente cliente){
		clienteController.create(cliente);
	}

	public List<Cliente> findAllClientes(){
		return clienteController.findClienteEntities();
	}

	public Cliente findClienteById(int idCliente) {
		return clienteController.findCliente(idCliente);
	}

	public void borrarClienteById(int idCliente) {
		try {
			clienteController.destroy(idCliente);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(ClientePersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void modificarCliente(Cliente cliente) {
		try {
			clienteController.edit(cliente);
		} catch (Exception ex) {
			Logger.getLogger(ClientePersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	
}
