package com.github.bilak.axonframework.poc.command.config;

import com.github.bilak.axonframework.poc.command.user.User;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

/**
 * Created by lvasek on 06/08/15.
 */
@Configuration
@ComponentScan(basePackageClasses = {User.class})
public class CommandConfiguration {


    @Autowired
	private PlatformTransactionManager transactionManager;

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean(name = "commandBus")
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setTransactionManager(new SpringTransactionManager(transactionManager));
        return commandBus;
    }


    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor(){
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
        CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
        factory.setCommandBus(commandBus());
        return factory;
    }

    @Bean(name = "userEventSourcingRepository")
    public EventSourcingRepository<User> userEventSourcingRepository(EntityManagerProvider entityManagerProvider, DataSource dataSource, EventBus eventBus) throws SQLException {
        JpaEventStore jpaEventStore = new JpaEventStore(entityManagerProvider);
        jpaEventStore.setDataSource(dataSource);

        //EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events/")));
        EventSourcingRepository<User> repository = new EventSourcingRepository<User>(User.class, jpaEventStore);
        repository.setEventBus(eventBus);
        return repository;
    }



    @Bean
    public AggregateAnnotationCommandHandler<User> annotationUserCommandHandler(@Qualifier("userEventSourcingRepository") EventSourcingRepository<User> eventSourcingRepository) {
        AggregateAnnotationCommandHandler<User> commandHandler = new AggregateAnnotationCommandHandler<User>(User.class, eventSourcingRepository);
        return commandHandler;
    }

}
