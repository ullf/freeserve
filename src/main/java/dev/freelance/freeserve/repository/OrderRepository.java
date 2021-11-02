package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.AbstractOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<AbstractOrder,Integer> {

    public AbstractOrder findByabstractName(String abstractName);
}
