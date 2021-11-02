package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.Milestone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone,Integer> {
}
