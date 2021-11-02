package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.BuyerClient;
import dev.freelance.freeserve.inter.ClientInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AbstractClientService implements ClientInterface {

    private ClientRepository clientRepository;
    private AbstractOrderService abstractOrderService;

    @Override
    public boolean isFreelancer() {
        return true;
    }

    @Override
    public boolean isBuyer() {
        return false;
    }

    @Override
    public AbstractClient createAbstractClient(String name,String surname,boolean indicator) {
        if (indicator == false) {
            AbstractClient buyer = new BuyerClient();
            buyer.setIndicator(indicator);
            buyer.setName(name);
            buyer.setSurname(surname);
            clientRepository.save(buyer);
            return buyer;
        } else {
            AbstractClient freelancer = new AbstractClient();
            freelancer.setIndicator(indicator);
            freelancer.setName(name);
            freelancer.setSurname(surname);
            clientRepository.save(freelancer);
            return freelancer;
        }
    }

    @Override
    public AbstractClient createClientOrder(int clientId,String orderName) {
          Optional<AbstractClient> optClient = clientRepository.findById(clientId);
          var client = optClient.get();
          if (client.isIndicator() == true) {
              var order = abstractOrderService.createOrder(orderName,null);
              client.getOrdersId().add(order);
              client.setOrdersId(client.getOrdersId());
              clientRepository.save(client);
          }
        return null;
    }
}
