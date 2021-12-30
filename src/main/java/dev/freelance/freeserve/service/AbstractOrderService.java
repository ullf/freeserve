package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AbstractOrderService implements OrderInterface {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Transactional
    public AbstractOrder createOrder(AbstractOrder order) {
        if (order.getClientsId() != null) {
            var client = clientRepository.findById(order.getClientsId().getId()).get();
            System.out.println(client.getId());
            if (client.isIndicator() == false ) {
                orderRepository.save(order);
                return order;
            }
        }
        return new AbstractOrder();
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
        return new AbstractOrder();
    }

    @Override
    public AbstractOrder checkOrder(int orderId) {
        Optional<AbstractOrder> op_order =  orderRepository.findById(orderId);
        return op_order.isEmpty() ? null : op_order.get();
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
