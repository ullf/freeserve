package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.BuyerClient;
import dev.freelance.freeserve.inter.ClientInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AbstractClientService implements ClientInterface {

    private ClientRepository clientRepository;

    @Override
    public boolean isFreelancer() {
        return true;
    }

    @Override
    public boolean isBuyer() {
        return false;
    }

    public ResponseEntity<?> createAbstractClient(AbstractClient abstractClient) {
        if (abstractClient.isIndicator() == false) {
            clientRepository.save(abstractClient);
            return ResponseEntity.ok(abstractClient);
        } else {
            abstractClient.setNickname("frl_"+abstractClient.getNickname());
            clientRepository.save(abstractClient);
            return ResponseEntity.ok(abstractClient);
        }
    }

    @Override
    public AbstractClient createAbstractClient(String name,String surname,boolean indicator) {
        if (indicator == false) {
            AbstractClient buyer = new BuyerClient();
            buyer.setIndicator(indicator);
            buyer.setName(name);
            buyer.setNickname(name);
            buyer.setSurname(surname);
            clientRepository.save(buyer);
            return buyer;
        } else {
            AbstractClient freelancer = new AbstractClient();
            freelancer.setIndicator(indicator);
            freelancer.setName(name);
            freelancer.setNickname("frl_"+name);
            freelancer.setSurname(surname);
            clientRepository.save(freelancer);
            return freelancer;
        }
    }

    public AbstractClient findAbstractClientByNickname(String nickname) {
        var client = clientRepository.findAbstractClientByNickname(nickname);
        return client;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.loadUserByUsername(username);
    }
}
