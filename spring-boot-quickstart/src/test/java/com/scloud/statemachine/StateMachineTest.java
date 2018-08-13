package com.scloud.statemachine;

import com.scloud.CommonTest;
import com.scloud.stateMachine.enums.OrderEventsEnum;
import com.scloud.stateMachine.enums.OrderStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;

/**
 * @author andy
 * @create 2018/8/13 22:56
 **/
public class StateMachineTest extends CommonTest{

    @Autowired
    private StateMachine<OrderStatusEnum,OrderEventsEnum> stateMachine;

    @Test
    public void test() {
        stateMachine.start();
        stateMachine.sendEvent(OrderEventsEnum.PAY);
        stateMachine.sendEvent(OrderEventsEnum.RECEIVE);
    }




}
