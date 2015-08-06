package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;

/**
 * Created by lvasek on 06/08/15.
 */
public class RegisterUserCommand extends AbstractUserCommand {

    private String userName;
    private String userEmail;

    public RegisterUserCommand(UserId userId, String userName, String userEmail) {
        Assert.notNull(userId, "UserId cannot be null");
        Assert.notEmpty(userName, "UserName cannot be null");
        Assert.notEmpty(userEmail, "UserEmail cannot be null");
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
