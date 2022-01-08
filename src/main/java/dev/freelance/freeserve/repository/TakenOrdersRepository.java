package dev.freelance.freeserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.freelance.freeserve.entity.TakenOrders;

@Repository
public interface TakenOrdersRepository extends CrudRepository<TakenOrders,Integer> {

    @Query("select a from TakenOrders a where a.orderId = :id")
    public List<TakenOrders> findAllTakenByOrdersId(@Param("id") int id);
    
}