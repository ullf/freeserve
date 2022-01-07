package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.Milestone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone,Integer> {

    @Query("select a from Milestone a where a.orderId.abstractId = :id")
    public List<Milestone> findAllMilestonesByOrderId(@Param("id") int id);
}
