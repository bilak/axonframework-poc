package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.io.Serializable;

/**
 * Created by lvasek on 06/08/15.
 */
public abstract class AbstractUserCommand implements Serializable{

    private static final long serialVersionUID = -3042860166783390252L;

    @TargetAggregateIdentifier
    protected UserId userId;

    public UserId getUserId() {
        return userId;
    }
}
