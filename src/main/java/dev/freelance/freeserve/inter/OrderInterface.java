package dev.freelance.freeserve.inter;

import dev.freelance.freeserve.entity.AbstractOrder;

public interface OrderInterface {
    AbstractOrder createOrder(int clientId,String name, String description);
    int createMilestone();
    int completeOrder();
    int completeMilestone();
}
