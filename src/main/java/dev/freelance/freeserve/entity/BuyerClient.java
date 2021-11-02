package dev.freelance.freeserve.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BuyerClient extends AbstractClient {

    public BuyerClient(String name,String surname) {
        this.setIndicator(false);
        this.setName(name);
        this.setSurname(surname);
    }

}
