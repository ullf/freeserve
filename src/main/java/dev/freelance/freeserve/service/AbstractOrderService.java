package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.Milestone;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AbstractOrderService implements OrderInterface {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Transactional
    public AbstractOrder createOrder(AbstractOrder order) {
        var client = clientRepository.findById(order.getClientsId().getId()).get();
        System.out.println(client.getId());
        if (client.isIndicator() == true ) {
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public AbstractOrder createOrder(int clientId,String name,String description) {
        var client = clientRepository.findById(clientId).get();
        System.out.println(client.getId());
        if (client.isIndicator() == true ) {
            AbstractOrder order = new AbstractOrder();
            order.setAbstractName(name);
            order.setAbstractDescription(description);
            order.setClientsId(client);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public AbstractOrder checkOrder(int orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Milestone createMilestone(int orderId, String name, String description) {
        return null;
    }

    @Override
    public int completeOrder() {
        return 0;
    }

    @Override
    public int completeMilestone() {
        return 0;
    }
}
