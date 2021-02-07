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
@Table(name = "SKPEDIDO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO extends AuditoriaDTO implements Serializable {

    private static final long serialVersionUID = 2546520950297617689L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidoPk;

    @Column
    private Integer personaPk;

    @Column
    private Integer tipoPagoPk;

    @Column
    private String codigoPedido;

    @Column
    private Double totalPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaPk", referencedColumnName = "personaPk", insertable = false, updatable = false)
    private PersonaDTO persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoPagoPk", referencedColumnName = "tipoPagoPk", insertable = false, updatable = false)
    private TipoPagoDTO tipoPago;

}
