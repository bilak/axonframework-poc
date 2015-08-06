package com.github.bilak.axonframework.poc.command.user;

import com.github.bilak.axonframework.poc.domain.user.UserId;
import com.github.bilak.axonframework.poc.domain.user.UserRegisteredEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by lvasek on 06/08/15.
 */
public class User extends AbstractAnnotatedAggregateRoot<UserId>{

    @AggregateIdentifier
    private UserId userId;
    private String userName;
    private String userEmail;


    private User(){

    }

    public User(UserId userId, String userName, String userEmail){
        apply(new UserRegisteredEvent(userId, userName, userEmail));
    }

    @EventSourcingHandler
    @SuppressWarnings("unused")
    private void onUserRegistered(UserRegisteredEvent event){
        this.userId = event.getUserId();
        this.userName = event.getUserName();
        this.userEmail = event.getUserEmail();
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
