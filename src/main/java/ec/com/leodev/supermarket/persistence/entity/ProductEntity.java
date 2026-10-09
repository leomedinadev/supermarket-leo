package ec.com.leodev.supermarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Entity product.
 */
@Getter
@Setter
@Entity(name = "productos")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "id_categoria")
    private Integer idCategory;

    @Column(name = "codigo_barras")
    private String codeBar;

    @Column(name = "precio_venta")
    private Double priceSale;

    @Column(name = "cantidad_stock")
    private Integer quantityStock;

    @Column(name = "estado")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;
}
