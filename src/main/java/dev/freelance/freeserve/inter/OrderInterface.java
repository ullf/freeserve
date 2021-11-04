package dev.freelance.freeserve.inter;

import dev.freelance.freeserve.entity.AbstractOrder;
import dev.freelance.freeserve.entity.Milestone;

public interface OrderInterface {
    AbstractOrder createOrder(int clientId,String name, String description);
    AbstractOrder checkOrder(int orderId);
    Milestone createMilestone(int orderId,String name, String description);
    int completeOrder();
    int completeMilestone();
}
