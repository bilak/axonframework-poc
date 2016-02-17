package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;

/**
 * Created by lvasek on 25/08/15.
 */
public class UserName {

    private String firstName;
    private String lastName;


    public UserName(String fristName, String lastName){
        Assert.notEmpty(firstName, "First Name cannot be empty");
    }
}
