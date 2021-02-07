package ec.com.kardex.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "SKPEDIDODETALLE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = 8006864590221702723L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dpedidoDetallePk;

    @Column
    private Integer articuloPk;

    @Column
    private Integer pedidoPk;

    @Column
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articuloPk", referencedColumnName = "articuloPk", insertable = false, updatable = false)
    private ArticuloDTO articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidoPk", referencedColumnName = "pedidoPk", insertable = false, updatable = false)
    private PedidoDTO pedido;

}
