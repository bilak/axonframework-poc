package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by lvasek on 06/08/15.
 */
public abstract class AbstractUserCommand implements Serializable{

    @TargetAggregateIdentifier
    protected UserId userId;

    public UserId getUserId() {
        return userId;
    }
}
