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
@Table(name = "SKPERSONA")
@Getter
@Setter
public class PersonaDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = -2685457070684626591L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personaPk;

    @Column
    private Integer usuarioPk;

    @Column(unique = true)
    private String cedula;

    @Column
    private String nombres;

    @Column
    private String apellidos;

    @Column
    private String mail;

    @Column
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioPk", referencedColumnName = "usuarioPk", insertable = false, updatable = false)
    private UsuarioDTO usuario;

}
