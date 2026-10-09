package ec.com.leodev.supermarket.persistence.mapper;

import ec.com.leodev.supermarket.domain.vo.PurchaseItemVO;
import ec.com.leodev.supermarket.persistence.entity.PurchaseProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProduct", target = "productId"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "status", target = "active")
    })
    PurchaseItemVO toPurchaseItemVO(PurchaseProductEntity product);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchaseEntity", ignore = true),
            @Mapping(target = "productEntity", ignore = true),
            @Mapping(target = "id.idPurchase", ignore = true)
    })
    PurchaseProductEntity toPurchaseProductEntity(PurchaseItemVO item);
}
