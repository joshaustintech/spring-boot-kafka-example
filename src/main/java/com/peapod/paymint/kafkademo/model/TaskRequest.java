package com.peapod.paymint.kafkademo.model;

import org.springframework.lang.NonNull;

public class TaskRequest {
    @NonNull
    private String message;
    @NonNull
    private Boolean taskSucceeds;

    public TaskRequest(@NonNull String message, @NonNull Boolean taskSucceeds) {
        this.message = message;
        this.taskSucceeds = taskSucceeds;
    }

    public TaskRequest() {}

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public Boolean getTaskSucceeds() {
        return taskSucceeds;
    }

    public void setTaskSucceeds(@NonNull Boolean taskSucceeds) {
        this.taskSucceeds = taskSucceeds;
    }
}
