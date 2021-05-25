package com.peapod.paymint.kafkademo.service;

import com.peapod.paymint.kafkademo.model.Task;
import com.peapod.paymint.kafkademo.util.Groups;
import com.peapod.paymint.kafkademo.util.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    private final KafkaTemplate<String, Task> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(RetryService.class);

    @Autowired
    public RetryService(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = Topics.RETRY_TASK, groupId = Groups.TASK_GROUP)
    public void consumeRetryEvent(Task task) {
        if (task.getTaskRequest().getTaskSucceeds()) {
            log.info(
                    "Task with message '" +
                    task.getTaskRequest().getMessage() +
                    "' succeeded!"
            );
        } else if (task.getAttempts() <= 2) {
            log.info("Retry attempt " + task.getAttempts() +
                    " for message '" + task.getTaskRequest().getMessage() + "'");
            task.setAttempts(task.getAttempts() + 1);
            kafkaTemplate.send(Topics.RETRY_TASK, task);
        } else {
            kafkaTemplate.send(Topics.ERROR_NOTIFICATION, task);
        }
    }
}
