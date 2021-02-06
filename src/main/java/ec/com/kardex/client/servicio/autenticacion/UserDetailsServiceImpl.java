package ec.com.kardex.client.servicio.autenticacion;

import com.querydsl.core.QueryException;
import ec.com.kardex.client.dao.UsuarioAutenticacionDao;
import ec.com.kardex.client.dto.UsuarioDTO;
import ec.com.kardex.client.dto.UsuarioRolDTO;
import ec.com.kardex.client.exception.KardexExcepction;
import ec.com.kardex.client.vo.UserDetailVO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioAutenticacionDao usuarioAutenticacionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UsuarioDTO user = usuarioAutenticacionDao.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
            user.setRolesUsuario(obtenerRolesUsuario(user.getUsuarioPk()));
            return create(user);
        } catch (HibernateException e) {
            throw new KardexExcepction("No fue posible validar el usuario.", e);
        }
    }

    public List<UsuarioRolDTO> obtenerRolesUsuario(Integer userId) {
        try {
            if (userId == null) {
                throw new KardexExcepction("El codigo de usuario es obligatorio para obtener los roles");
            }
            return usuarioAutenticacionDao.obtenerRolesUsuario(userId);
        } catch (QueryException e) {
            throw new KardexExcepction("No fue posible ontener los roles asociado al asuario", e);
        }
    }

    private UserDetailVO create(UsuarioDTO user) {
        UserDetailVO userDetail = new UserDetailVO();
        userDetail.setUsername(user.getNombreUsuario());
        userDetail.setPassword(user.getPassword());
        userDetail.setEstado(user.isEstado());
        userDetail.setRolesUsuario(mapToRolesUsuario(user.getRolesUsuario()));
        return userDetail;
    }

    private List<GrantedAuthority> mapToRolesUsuario(List<UsuarioRolDTO> rolesUsuario) {
        return rolesUsuario.stream().map(role -> new SimpleGrantedAuthority(role.getRol().getNombreRol()))
                .collect(Collectors.toList());
    }

}
