package com.github.bilak.axonframework.poc.domain.user;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by lvasek on 06/08/15.
 */
public class UserId implements Serializable {


    private static final long serialVersionUID = -2353343328405294338L;
    private String identifier;

    public UserId() {
        this(IdentifierFactory.getInstance().generateIdentifier());
    }

    public UserId(String identifier) {
        Assert.notEmpty(identifier, "Identifier cannot be emtpy");
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserId other = (UserId) obj;
        return Objects.equals(this.identifier, other.identifier);
    }


    @Override
    public String toString() {
        return identifier;
    }
}
