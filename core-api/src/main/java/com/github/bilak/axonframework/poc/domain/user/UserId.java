package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

/**
 * Created by lvasek on 06/08/15.
 */
public class UserId {

    private String identifier;

    public UserId(){
        this(IdentifierFactory.getInstance().generateIdentifier());
    }

    public UserId(String identifier){
        Assert.notEmpty(identifier, "Identifier cannot be emtpy");
        this.identifier = identifier;
    }
}
