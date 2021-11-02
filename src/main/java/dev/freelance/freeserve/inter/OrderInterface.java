package dev.freelance.freeserve.inter;

import dev.freelance.freeserve.entity.AbstractOrder;

public interface OrderInterface {
    AbstractOrder createOrder(String name, String description);
    int createMilestone();
    int completeOrder();
    int completeMilestone();
}
