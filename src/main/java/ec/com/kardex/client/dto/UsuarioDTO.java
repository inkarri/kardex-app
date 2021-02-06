package ec.com.kardex.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SKUSUARIO")
@Getter
@Setter
public class UsuarioDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = 2356997092196743244L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioPk;

    @Column(name = "NOMBREUSUARIO", unique = true)
    private String nombreUsuario;

    @Column
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRolDTO> rolesUsuario;


}
