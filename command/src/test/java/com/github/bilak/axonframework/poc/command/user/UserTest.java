package com.github.bilak.axonframework.poc.command.user;

import com.github.bilak.axonframework.poc.TestConfig;
import com.github.bilak.axonframework.poc.domain.user.RegisterUserCommand;
import com.github.bilak.axonframework.poc.domain.user.UserId;
import com.github.bilak.axonframework.poc.domain.user.UserRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lvasek on 06/08/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
public class UserTest {

    private FixtureConfiguration fixture;

    @Before
    public void setup() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
        //UserCommandHandler target = (UserCommandHandler) ((Advised) userCommandHandler).getTargetSource().getTarget();
        UserCommandHandler target = new UserCommandHandler();
        target.setUserRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(target);
    }

    @Test
    public void testRegisterUser(){
        RegisterUserCommand command = new RegisterUserCommand(new UserId(), "myname", "my@email.com");
        UserRegisteredEvent event = new UserRegisteredEvent(command.getUserId(), "myname", "my@email.com");
        fixture.given().when(command).expectEvents(event);
    }
}
