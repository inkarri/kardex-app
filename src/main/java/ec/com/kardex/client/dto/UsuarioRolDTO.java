package ec.com.kardex.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SKUSUARIOROL")
@Getter
@Setter
public class UsuarioRolDTO extends AuditoriaDTO implements Serializable {

	private static final long serialVersionUID = -3748218313850586342L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioRolPk;

	@Column(name = "USUARIOPK")
	private Integer usuarioPk;

	@Column(name = "ROLPK")
	private Integer rolPk;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLPK", referencedColumnName = "ROLPK", insertable = false, updatable = false)
	private RolDTO rol;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIOPK", referencedColumnName = "usuarioPk", insertable = false, updatable = false)
	private UsuarioDTO usuario;

}
