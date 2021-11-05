package dev.freelance.freeserve.entity;

import lombok.Data;


public class AuthRequest {
    private String nickname;
    private String surname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
