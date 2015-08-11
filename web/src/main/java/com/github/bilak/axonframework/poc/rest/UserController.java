package com.github.bilak.axonframework.poc.rest;

import com.github.bilak.axonframework.poc.domain.user.ChangeEmailCommand;
import com.github.bilak.axonframework.poc.domain.user.RegisterUserCommand;
import com.github.bilak.axonframework.poc.domain.user.UserId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by lvasek on 10/08/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser() {
        commandGateway.send(new RegisterUserCommand(new UserId(), "lukas", "my@email.com"));
    }

    @RequestMapping(value = "/changeEmail/{userId}/{email}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void changeEmail(@PathVariable(value = "userId") String userId, @PathVariable("email") String email) {
        commandGateway.send(new ChangeEmailCommand(new UserId(userId), email));
    }


}
