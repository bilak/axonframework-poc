package com.github.bilak.axonframework.poc.domain.user;

import java.io.Serializable;

/**
 * Created by lvasek on 10/08/15.
 */
public class AbstractUserEvent implements Serializable {

    private static final long serialVersionUID = -2327753222241359015L;

    protected UserId userId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
