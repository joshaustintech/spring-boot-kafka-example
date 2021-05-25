package com.peapod.paymint.kafkademo.controller;

import com.peapod.paymint.kafkademo.model.TaskRequest;
import com.peapod.paymint.kafkademo.model.TaskResponse;
import com.peapod.paymint.kafkademo.service.TaskService;
import com.peapod.paymint.kafkademo.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskService taskService;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public TaskResponse doTask(@RequestBody TaskRequest request) {
        TaskResponse response = new TaskResponse();
        try {
            taskService.doTask(request);
            response.setStatus(Status.SUBMITTED);
            return response;
        } catch (Exception e) {
            log.error(e.getClass().toString() + " - " + e.getMessage());
            response.setStatus(Status.FAILED);
            return response;
        }
    }

}
