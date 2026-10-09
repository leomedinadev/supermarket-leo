package ec.com.leodev.supermarket.domain.service;

import ec.com.leodev.supermarket.domain.repository.IProductRepository;
import ec.com.leodev.supermarket.domain.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Lazy
    @Autowired
    private IProductRepository productRepository;

    public List<ProductVO> getAll() {
        return productRepository.getAll();
    }

    public Optional<ProductVO> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<ProductVO>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public ProductVO save(ProductVO product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

}
