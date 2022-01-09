package dev.freelance.freeserve.repository;

import dev.freelance.freeserve.entity.AbstractClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<AbstractClient,Integer> {

    @Query("select a from AbstractClient a where a.nickname = :nickname")
    AbstractClient findAbstractClientByNickname(@Param("nickname") String nickname);

    @Query("select a from AbstractClient a where a.nickname = :nickname")
    UserDetails loadUserByUsername( @Param("nickname") String nickname)
            throws UsernameNotFoundException;
}
