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
@Table(name = "SKCLASIFICACION")
@Getter
@Setter
public class ClasificacionDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = 5434141923211416068L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clasificacionPk;

    @Column
    private String nombre;

}
