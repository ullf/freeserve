package dev.freelance.freeserve.inter;

import dev.freelance.freeserve.entity.AbstractClient;
import dev.freelance.freeserve.entity.AbstractOrder;

public interface ClientInterface {
    boolean isFreelancer();
    boolean isBuyer();
    AbstractClient createAbstractClient(String name,String surname,boolean indicator);
   // AbstractClient createClientOrder(int clientId,String orderName);
}