package com.github.bilak.axonframework.poc.command.config;

import com.github.bilak.axonframework.poc.command.user.User;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventCountSnapshotterTrigger;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SpringAggregateSnapshotter;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by lvasek on 06/08/15.
 */
@Configuration
public class UserCommandConfiguration {

    @Bean(name = "userEventSourcingRepository")
    public EventSourcingRepository<User> userEventSourcingRepository(JpaEventStore jpaEventStore, EventBus eventBus, @Qualifier("userSnapshotterTrigger") EventCountSnapshotterTrigger snapshotterTrigger) throws SQLException {
        EventSourcingRepository<User> repository = new EventSourcingRepository<User>(User.class, jpaEventStore);
        repository.setEventBus(eventBus);
        repository.setSnapshotterTrigger(snapshotterTrigger);
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler<User> annotationUserCommandHandler(@Qualifier("userEventSourcingRepository") EventSourcingRepository<User> eventSourcingRepository) {
        AggregateAnnotationCommandHandler<User> commandHandler = new AggregateAnnotationCommandHandler<User>(User.class, eventSourcingRepository);
        return commandHandler;
    }

    @Bean(name = "userSnapshotterTrigger")
    public EventCountSnapshotterTrigger snapshotterTrigger(@Qualifier("userSnapshotter") SpringAggregateSnapshotter snapshotter) {
        EventCountSnapshotterTrigger snapshotterTrigger = new EventCountSnapshotterTrigger();
        snapshotterTrigger.setSnapshotter(snapshotter);
        snapshotterTrigger.setTrigger(10);
        return snapshotterTrigger;
    }

    @Bean(name = "userSnapshotter")
    public SpringAggregateSnapshotter snapshotter(JpaEventStore eventStore, @Qualifier(value = "axonExecutor") ScheduledThreadPoolExecutor axonExecutor, PlatformTransactionManager transactionManager) {
        SpringAggregateSnapshotter snapshotter = new SpringAggregateSnapshotter();
        snapshotter.setEventStore(eventStore);
        snapshotter.setExecutor(axonExecutor);
        snapshotter.setTransactionManager(transactionManager);
        snapshotter.setAggregateFactories(Arrays.asList(new GenericAggregateFactory<User>(User.class)));
        return snapshotter;
    }
}
