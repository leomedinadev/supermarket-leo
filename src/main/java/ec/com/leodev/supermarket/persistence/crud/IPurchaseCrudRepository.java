package ec.com.leodev.supermarket.persistence.crud;

import ec.com.leodev.supermarket.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPurchaseCrudRepository extends CrudRepository<PurchaseEntity, Integer> {

    Optional<List<PurchaseEntity>> findByIdClient(String idClient);
}
