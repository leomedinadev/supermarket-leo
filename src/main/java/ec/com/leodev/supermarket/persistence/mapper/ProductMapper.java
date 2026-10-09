package ec.com.leodev.supermarket.persistence.mapper;

import ec.com.leodev.supermarket.domain.vo.ProductVO;
import ec.com.leodev.supermarket.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "id", target = "productId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "priceSale", target = "price"),
            @Mapping(source = "quantityStock", target = "stock"),
            @Mapping(source = "status", target = "active"),
            @Mapping(source = "categoryEntity", target = "category"),
    })
    ProductVO toProductVO(ProductEntity productEntity);

    List<ProductVO> toProducts(List<ProductEntity> products);

    @InheritInverseConfiguration
    @Mapping(target = "codeBar", ignore = true)
    ProductEntity toProductEntity(ProductVO product);

}
