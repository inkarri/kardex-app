package ec.com.kardex.client.dao;

import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.dto.UsuarioRolDTO;

import java.util.List;

public interface UsuarioAutenticacionDao {

	UsuarioDTO findByUsername(String username);

	List<UsuarioRolDTO> obtenerRolesUsuario(Integer userId);

}
