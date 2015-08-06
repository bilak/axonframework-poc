package com.github.bilak.axonframework.poc.domain.user;

/**
 * Created by lvasek on 06/08/15.
 */
public class UserRegisteredEvent {

    private UserId userId;
    private String userName;
    private String userEmail;

    public UserRegisteredEvent(UserId userId, String userName, String userEmail){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
