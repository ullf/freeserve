package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.AbstractOrder;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<AbstractOrder,Integer> {

    public AbstractOrder findByabstractName(String abstractName);
    @Query("select a from AbstractOrder a where a.clientsId.id = :id")
    public List<AbstractOrder> findAllOrders(@Param("id") int id);
}
