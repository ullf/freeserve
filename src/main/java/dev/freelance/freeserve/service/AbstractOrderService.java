package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AbstractOrderService implements OrderInterface {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public AbstractOrder createOrder(String name,String description) {
        AbstractOrder order = new AbstractOrder();
        order.setAbstractName(name);
        order.setAbstractDescription(description);
        orderRepository.save(order);
        return order;
    }

    @Override
    public int createMilestone() {
        return 0;
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
