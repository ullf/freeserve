package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.TakenOrders;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.ClientRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import dev.freelance.freeserve.repository.TakenOrdersRepository;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AbstractOrderService implements OrderInterface {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private TakenOrdersRepository takenOrdersRepository;

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

    public ResponseEntity<?> takeOrder(int id) {
        var order = orderRepository.findById(id);
        if (order.isPresent()) {
            var obj = (AbstractClient)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(obj.isIndicator()+ " "+obj.getNickname());
            if(obj.isIndicator()) {
                TakenOrders taken = new TakenOrders();
                taken.setOrderId(id);
                taken.setFreelancerId(obj.getId());
                takenOrdersRepository.save(taken);
                System.out.println("taken!");
            }
        }
        return ResponseEntity.status(404).body("No order found with such order id");
    }

    public ResponseEntity<?> getTakenOrders(int clientId) {
        var client = clientRepository.findById(clientId);
        if (client.isPresent()) {
            System.out.println("OK");
            var obj = (AbstractClient)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj.isIndicator()) {
                System.out.println("OK");
                var t = takenOrdersRepository.findAllTakenByClientId(clientId);
                var list = new ArrayList<AbstractOrder>();
                for(int i=0;i<t.size();i++) {
                    list.add(orderRepository.findById(t.get(i).getOrderId()).get());
                }
                return ResponseEntity.ok(list);
            }
        }
        return ResponseEntity.status(404).body("No orders");
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
        var op_order = orderRepository.findById(orderId);
        if (op_order.isPresent()) {
            var order = op_order.get();
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
