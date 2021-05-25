package com.peapod.paymint.kafkademo.model;

public class Task {
    int attempts;
    TaskRequest taskRequest;

    public Task(int attempts, TaskRequest taskRequest) {
        this.attempts = attempts;
        this.taskRequest = taskRequest;
    }

    public Task() {}

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public TaskRequest getTaskRequest() {
        return taskRequest;
    }

    public void setTaskRequest(TaskRequest taskRequest) {
        this.taskRequest = taskRequest;
    }
}
