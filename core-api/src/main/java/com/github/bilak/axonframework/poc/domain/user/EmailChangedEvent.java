package com.github.bilak.axonframework.poc.domain.user;

/**
 * Created by lvasek on 10/08/15.
 */
public class EmailChangedEvent extends AbstractUserEvent {


    private static final long serialVersionUID = 803398049924865749L;

    private String email;

    public EmailChangedEvent(UserId userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
