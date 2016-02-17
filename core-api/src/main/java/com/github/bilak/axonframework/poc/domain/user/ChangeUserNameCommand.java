package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;

/**
 * Created by lvasek on 25/08/15.
 */
public class ChangeUserNameCommand {

    private UserName userName;



    public ChangeUserNameCommand(UserName userName){
        Assert.notNull(userName, "username cannot be empty");
        this.userName = userName;
    }
}
