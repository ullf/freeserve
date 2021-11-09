package dev.freelance.freeserve.entity;

import lombok.Data;


public class AuthRequest {
    private String nickname;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
