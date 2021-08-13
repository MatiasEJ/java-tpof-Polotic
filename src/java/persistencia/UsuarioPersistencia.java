/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.modelos.personas.Usuario;

/**
 *
 * @author keta
 */
public class UsuarioPersistencia {
	UsuarioJpaController controller = new UsuarioJpaController();

	public void crearUsuario(Usuario usuario){
		controller.create(usuario);
	}

	public List<Usuario> findAllUsuario(){
		return controller.findUsuarioEntities();
	}

	public Usuario findUsuarioById(int id) {
		return controller.findUsuario(id);
	}

	public void modificarUsuario(Usuario usuario) {
		try {
			controller.edit(usuario);
		} catch (Exception ex) {
			Logger.getLogger(UsuarioPersistencia.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Usuario findUsuarioByUsername(String username){
		for(Usuario usu: controller.findUsuarioEntities()){
			if(usu.getNombreUsuario().equals(username)){
			return usu;}
		}
		return null;
	}
	
}
