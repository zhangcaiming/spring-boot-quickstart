package com.scloud.async;

import com.scloud.CommonTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

public class TaskTest extends CommonTest {

    @Autowired
    private Task task;

    @Test
    public void test() throws Exception{

        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTow();
        Future<String> task3 = task.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成,总耗时：" + (end - start) + "毫秒");


    }


}
