package ec.com.leodev.supermarket.domain.repository;

import ec.com.leodev.supermarket.domain.vo.ProductVO;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    List<ProductVO> getAll();
    Optional<List<ProductVO>> getByCategory(int categoryId);
    Optional<List<ProductVO>> getScarseProducts(int quantity);
    Optional<ProductVO> getProduct(int productId);
    ProductVO save(ProductVO product);
    void delete(int productId);

}
