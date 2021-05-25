package com.peapod.paymint.kafkademo.service;

import com.peapod.paymint.kafkademo.model.Task;
import com.peapod.paymint.kafkademo.model.TaskRequest;
import com.peapod.paymint.kafkademo.util.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final KafkaTemplate<String, Task> kafkaTemplate;

    @Autowired
    public TaskService(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // pretend that we try to do a task, it fails
    // so we send it to the Kafka queue for retries
    public void doTask(TaskRequest request) {
        Task task = new Task();
        task.setAttempts(0);
        task.setTaskRequest(request);
        kafkaTemplate.send(Topics.RETRY_TASK, task);
    }

}
