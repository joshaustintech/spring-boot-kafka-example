package com.peapod.paymint.kafkademo.service;

import com.peapod.paymint.kafkademo.model.Task;
import com.peapod.paymint.kafkademo.util.Groups;
import com.peapod.paymint.kafkademo.util.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ErrorNotificationService {

    private static final Logger log = LoggerFactory.getLogger(ErrorNotificationService.class);

    @KafkaListener(topics = Topics.ERROR_NOTIFICATION, groupId = Groups.TASK_GROUP)
    public void consumeErrorNotification(Task task) {
        log.error("This message has exceeded task attempts: '" +
                task.getTaskRequest().getMessage() + "'");
    }

}
