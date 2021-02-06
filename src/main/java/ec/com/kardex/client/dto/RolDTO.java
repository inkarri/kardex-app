package ec.com.kardex.client.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SKROL")
@Getter
@Setter
public class RolDTO extends AuditoriaDTO implements Serializable {

	private static final long serialVersionUID = 5941826674793217365L;

	@Id
	@Column(name = "ROLPK")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rolPk;

	@Column(name = "NOMBREROL")
	private String nombreRol;

}
