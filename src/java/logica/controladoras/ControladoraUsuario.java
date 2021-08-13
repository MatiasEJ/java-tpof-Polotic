/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladoras;

import java.util.List;
import logica.entidades.personas.Usuario;
import persistencia.UsuarioPersistencia;

/**
 *
 * @author keta
 */
public class ControladoraUsuario {

	UsuarioPersistencia persistencia = new UsuarioPersistencia();

	public Usuario crearUsuario(String nombreUsuario, String password) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setPassword(password);

		persistencia.crearUsuario(usuario);
		return usuario;
	}

	public List<Usuario> findAllUsuario() {
		return persistencia.findAllUsuario();
	}

	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
		List<Usuario> listaUsuarios = findAllUsuario();
		for (Usuario usario : listaUsuarios) {
			if (nombreUsuario.equals(usario.getNombreUsuario())) {
				return usario;
			}
		}
		return null;
	}


	public Usuario findUsuarioById(int id) {
		return persistencia.findUsuarioById(id);
	}

	public void modificarUsuario(Usuario usuario) {
		persistencia.modificarUsuario(usuario);
	}

	public Usuario findUsuarioByUsername(String username) {
		return persistencia.findUsuarioByUsername(username);
	}

}
