package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;

/**
 * Created by lvasek on 10/08/15.
 */
public class ChangeEmailCommand extends AbstractUserCommand {

    private static final long serialVersionUID = -6600963746944832402L;

    private String email;

    public ChangeEmailCommand(UserId userId, String email) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.notEmpty(email, "Email cannot be empty");
        this.userId = userId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
