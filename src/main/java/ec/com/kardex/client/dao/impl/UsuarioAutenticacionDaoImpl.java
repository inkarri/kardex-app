package ec.com.kardex.client.dao.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import ec.com.kardex.client.dao.CommonsDao;
import ec.com.kardex.client.dao.UsuarioAutenticacionDao;
import ec.com.kardex.client.dto.RolDTO;
import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.dto.UsuarioRolDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ec.com.kardex.client.dto.QUsuarioRolDTO.usuarioRolDTO;
import static ec.com.kardex.client.dto.QUsuarioDTO.usuarioDTO;
import static ec.com.kardex.client.dto.QRolDTO.rolDTO;


@Repository
public class UsuarioAutenticacionDaoImpl extends CommonsDao implements UsuarioAutenticacionDao {

	@Override
	public UsuarioDTO findByUsername(String username) {
		JPAQuery<UsuarioDTO> query = new JPAQuery<>(em);
		return query.from(usuarioDTO)
				.where(usuarioDTO.nombreUsuario.eq(username))
				.fetchOne();
	}

	@Override
	public List<UsuarioRolDTO> obtenerRolesUsuario(Integer userId) {
		JPAQuery<UsuarioRolDTO> query = new JPAQuery<>(em);
		return query.from(usuarioRolDTO)
				.innerJoin(usuarioRolDTO.rol, rolDTO)
				.where(usuarioRolDTO.usuarioPk.eq(userId),
						usuarioRolDTO.estado.eq(Boolean.TRUE))
				.select(Projections.bean(UsuarioRolDTO.class,
						usuarioRolDTO.usuarioRolPk,
						usuarioRolDTO.usuarioPk,
						usuarioRolDTO.rolPk,
						Projections.bean(RolDTO.class,
								rolDTO.rolPk, rolDTO.nombreRol)
								.as(usuarioRolDTO.rol)))
				.fetch();
	}

}
