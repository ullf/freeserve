package dev.freelance.freeserve.service;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.Milestone;
import dev.freelance.freeserve.inter.OrderInterface;
import dev.freelance.freeserve.repository.MilestoneRepository;
import dev.freelance.freeserve.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilestoneService implements OrderInterface {

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public AbstractOrder createOrder(int clientId, String name, String description) {
        return null;
    }

    @Override
    public AbstractOrder checkOrder(int orderId) {
        return null;
    }

    @Override
    public Milestone createMilestone(int orderId,String name, String description) {
        var order = orderRepository.findById(orderId).get();
        if (order != null) {
            Milestone milestone = new Milestone();
            milestone.setMilestoneName(name);
            milestone.setMilestoneDescription(description);
            milestone.setOrderId(order);
            milestoneRepository.save(milestone);
            return milestone;
        }
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
