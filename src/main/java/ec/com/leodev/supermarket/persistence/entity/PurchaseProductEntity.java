package ec.com.leodev.supermarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "compras_productos")
public class PurchaseProductEntity implements Serializable {

    @EmbeddedId
    private PurchaseProductID id;

    @Column(name = "cantidad")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private PurchaseEntity purchaseEntity;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductEntity productEntity;

}
