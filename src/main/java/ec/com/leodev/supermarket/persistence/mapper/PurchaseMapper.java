package ec.com.leodev.supermarket.persistence.mapper;

import ec.com.leodev.supermarket.domain.vo.PurchaseVO;
import ec.com.leodev.supermarket.persistence.entity.PurchaseEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "id", target = "purchaseId"),
            @Mapping(source = "idClient", target = "clientId"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "middlePayment", target = "paymentMethod"),
            @Mapping(source = "comment", target = "comment"),
            @Mapping(source = "status", target = "state"),
            @Mapping(source = "productCol", target = "items")
    })
    PurchaseVO toPurchase(PurchaseEntity purchaseEntity);

    List<PurchaseVO> toPurchases(List<PurchaseEntity> purchaseEntityList);

    @InheritInverseConfiguration
    @Mapping(target = "clientEntity", ignore = true)
    PurchaseEntity toPurchaseEntity(PurchaseVO purchase);
}
