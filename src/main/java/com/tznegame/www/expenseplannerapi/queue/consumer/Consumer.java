package com.tznegame.www.expenseplannerapi.queue.consumer;

import com.tznegame.www.expenseplannerapi.queue.config.MessagingConfig;
import com.tznegame.www.expenseplannerapi.queue.dto.ExpenseStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumneMessageFromQueue(ExpenseStatus expenseStatus){
        System.out.println("Message received from queue: " + expenseStatus);
    }
}
