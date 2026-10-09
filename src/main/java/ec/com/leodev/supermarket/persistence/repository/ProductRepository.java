package ec.com.leodev.supermarket.persistence.repository;

import ec.com.leodev.supermarket.domain.repository.IProductRepository;
import ec.com.leodev.supermarket.domain.vo.ProductVO;
import ec.com.leodev.supermarket.persistence.crud.IProductCrudRepository;
import ec.com.leodev.supermarket.persistence.entity.ProductEntity;
import ec.com.leodev.supermarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private IProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<ProductVO> getAll() {
        List<ProductEntity> products = (List<ProductEntity>) productCrudRepository.findAll();
        return mapper.toProducts(products);
    }

    @Override
    public Optional<List<ProductVO>> getByCategory(int categoryId) {
        List<ProductEntity> productsEntity = productCrudRepository.findByIdCategoryOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProducts(productsEntity));
    }

    @Override
    public Optional<List<ProductVO>> getScarseProducts(int quantity) {
        Optional<List<ProductEntity>> products = productCrudRepository.findByQuantityStockLessThanAndStatus(quantity, true);
        return products.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<ProductVO> getProduct(int productId) {
        return productCrudRepository.findById(productId).map(product -> mapper.toProductVO(product));
    }

    @Override
    public ProductVO save(ProductVO product) {
        ProductEntity productEntity = mapper.toProductEntity(product);
        return mapper.toProductVO(productCrudRepository.save(productEntity));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
