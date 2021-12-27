package dev.freelance.freeserve.inter;

import dev.freelance.freeserve.entity.Milestone;

public interface MilestoneInterface {

    int createMilestone(Milestone milestone);
    Milestone createMilestone(int orderId,String name, String description);
    int completeMilestone();
}
