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
@Table(name = "SKARTICULO")
@Getter
@Setter
public class ArticuloDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = -7411091658379483838L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articuloPk;

    @Column
    private Integer clasificacionPk;

    private String codigoBarras;

    private String descripcion;

    private Double precio;

    private Integer existencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clasificacionPk", referencedColumnName = "clasificacionPk", insertable = false, updatable = false)
    private ClasificacionDTO clasificacion;

}
