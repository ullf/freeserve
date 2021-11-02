package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.OrderInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AbstractOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int abstractId;
    private String abstractName;
    private String abstractDescription;
    @OneToMany
    private List<Milestone> milestones = new ArrayList<>();
    @OneToOne
    @JoinColumn(columnDefinition = "id")
    private AbstractClient clientId;
}
