package com.scloud.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Create by andy on 2018/5/14
 * 定时任务 在{@link com.scloud.QuickstartApplication}中启用定时任务@EnableScheduling
 */
@Slf4j
@Component
public class SchedulerTask {

    private int count=0;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每隔1小时执行一次
     */
    @Scheduled(cron = "0 0 0-23 * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

    /**
     * 每隔1小时执行一次
     */
    @Scheduled(fixedRate = 60 * 60 * 1000)
    private void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }


}
