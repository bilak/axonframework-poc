package com.github.bilak.axonframework.poc.domain.user;

/**
 * Created by lvasek on 25/08/15.
 */
public class UserCommand2 extends ChangeUserNameCommand {

    public UserCommand2(UserName userName) {
        super(userName);
    }
}
