package ec.com.leodev.supermarket.persistence.repository;

import ec.com.leodev.supermarket.domain.repository.IPurchaseRepository;
import ec.com.leodev.supermarket.domain.vo.PurchaseVO;
import ec.com.leodev.supermarket.persistence.crud.IPurchaseCrudRepository;
import ec.com.leodev.supermarket.persistence.entity.PurchaseEntity;
import ec.com.leodev.supermarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements IPurchaseRepository {

    @Autowired
    private IPurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<PurchaseVO> getAll() {
        return mapper.toPurchases((List<PurchaseEntity>) purchaseCrudRepository.findAll());
    }

    @Override
    public Optional<List<PurchaseVO>> getByClient(String clientId) {
        return purchaseCrudRepository.findByIdClient(clientId)
                .map(purchaseEntityList -> mapper.toPurchases(purchaseEntityList));
    }

    @Override
    public PurchaseVO save(PurchaseVO purchase) {
        PurchaseEntity purchaseEntity = mapper.toPurchaseEntity(purchase);
        purchaseEntity.getProductCol().forEach(product -> product.setPurchaseEntity(purchaseEntity));

        return mapper.toPurchase(purchaseCrudRepository.save(purchaseEntity));
    }
}
