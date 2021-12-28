package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
        if (client.isIndicator() == false ) {
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    public AbstractOrder takeOrder(int id) {
        var order = orderRepository.findById(id).get();
        if (order != null) {
            
        }
        return null;
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

    @Transactional
    @Override
    public int completeOrder(int orderId) {
        var order = orderRepository.findById(orderId).get();
        if (order != null) {
            order.setCompleted(true);
            orderRepository.save(order);
            return 0;
        }
        return -1;
    }

    @Override
    public List<AbstractOrder> getAllOrdersById(int clientId) {
        var list = orderRepository.findAllOrdersById(clientId);
        System.out.println(list.size());
        return list;
    }
}
