package ec.com.kardex.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "CLASIFICACIONPK")
    private Integer clasificacionPk;

    @Column(name = "CODIGOBARRAS")
    private String codigoBarras;

    @Column
    private String descripcion;

    @Column
    private Double precio;

    @Column
    private Integer existencia;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASIFICACIONPK", referencedColumnName = "clasificacionPk", insertable = false, updatable = false)
    private ClasificacionDTO clasificacion;

}
