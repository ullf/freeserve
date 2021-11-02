package dev.freelance.freeserve.entity;

import dev.freelance.freeserve.inter.ClientInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "abstract_client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AbstractClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "abstract_name")
    private String name;
    @Column(name = "abstract_surname")
    private String surname;
    @Column(name = "indicator")
    private boolean indicator; // true: freelancer false: buyer
    @OneToMany
    private List<AbstractOrder> ordersId = new ArrayList<>();
}
