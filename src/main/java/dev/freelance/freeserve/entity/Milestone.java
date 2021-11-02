package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.MilestoneInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Milestone implements MilestoneInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int milestoneId;
    private String milestoneName;
    private String milestoneDescription;
    @ManyToOne
    private AbstractOrder orderId;

    @Override
    public int createMilestone() {
        return 0;
    }

    @Override
    public int completeMilestone() {
        return 1;
    }
}

