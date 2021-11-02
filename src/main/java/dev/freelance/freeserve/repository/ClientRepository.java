package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.AbstractClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<AbstractClient,Integer> {
}
