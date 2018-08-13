package com.scloud.stateMachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 改配置实现了com.scloud.stateMachine.config.StateMachineConfig 类中定义的状态机监听器实现
 *
 * @author andy
 * @create 2018/8/13 22:31
 **/
@WithStateMachine
@Slf4j
public class OrderEventConfig {

    @OnTransition(target = "UNPAID")
    public void create() {
        log.info("订单创建,待支付");
    }

    @OnTransition(source = "UNPAID",target = "WAITING_FOR_RECEIVE")
    public void pay() {
        log.info("用户完成支付,待收货");
    }

    @OnTransitionStart(source = "UNPAID",target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        log.info("用户完成支付,待收货：start");
    }

    @OnTransitionEnd(source = "UNPAID",target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        log.info("用户完成支付,待收货：end");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE",target = "DONE")
    public void recive() {
        log.info("用户已收货,订单完成");
    }


}
