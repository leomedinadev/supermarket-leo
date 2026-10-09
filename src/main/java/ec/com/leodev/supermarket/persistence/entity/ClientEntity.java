package ec.com.leodev.supermarket.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name="clientes")
public class ClientEntity implements Serializable {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="nombre")
    private String name;

    @Column(name="apellidos")
    private String lastName;

    @Column(name="celular")
    private Long cellPhone;

    @Column(name="direccion")
    private String address;

    @Column(name="correo_electronico")
    private String email;

    @OneToMany(mappedBy = "clientEntity")
    private List<PurchaseEntity> purchaseCol;
}
