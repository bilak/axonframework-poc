package com.github.bilak.axonframework.poc.command.user;

import com.github.bilak.axonframework.poc.domain.user.RegisterUserCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by lvasek on 06/08/15.
 */
@Component
public class UserCommandHandler {

    private Repository<User> userRepository;

    @CommandHandler
    @SuppressWarnings("unused")
    private void handleRegisterUser(RegisterUserCommand command){
        User user= new User(command.getUserId(), command.getUserName(), command.getUserEmail());
        userRepository.add(user);
    }


    @Autowired
    @Qualifier("userEventSourcingRepository")
    public void setUserRepository(Repository<User> userRepository){
        this.userRepository = userRepository;
    }
}
