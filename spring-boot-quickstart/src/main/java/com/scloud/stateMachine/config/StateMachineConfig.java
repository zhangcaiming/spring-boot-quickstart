package com.scloud.stateMachine.config;

import com.scloud.stateMachine.enums.OrderEventsEnum;
import com.scloud.stateMachine.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author andy
 * @create 2018/8/13 22:27
 **/
@Configuration
@EnableStateMachine
@Slf4j
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatusEnum,OrderEventsEnum>{

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventsEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStatusEnum.UNPAID)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventsEnum> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(OrderStatusEnum.UNPAID).target(OrderStatusEnum.WAITING_FOR_RECEIVE)
                    .event(OrderEventsEnum.PAY)
                    .and()
                .withExternal()
                    .source(OrderStatusEnum.WAITING_FOR_RECEIVE).target(OrderStatusEnum.DONE)
                    .event(OrderEventsEnum.RECEIVE);

    }
}
