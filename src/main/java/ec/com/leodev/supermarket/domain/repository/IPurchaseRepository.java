package ec.com.leodev.supermarket.domain.repository;

import ec.com.leodev.supermarket.domain.vo.PurchaseVO;

import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository {
    List<PurchaseVO> getAll();
    Optional<List<PurchaseVO>> getByClient(String clientId);
    PurchaseVO save(PurchaseVO purchase);
}
