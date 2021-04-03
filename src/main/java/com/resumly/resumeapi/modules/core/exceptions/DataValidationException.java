package com.resumly.resumeapi.modules.core.exceptions;

import com.resumly.resumeapi.core.domain.Message;

import java.util.List;

public class DataValidationException extends CustomException {

    public DataValidationException() {
    }


    public DataValidationException(Message... messages) {
        this.messages = messages;
    }


    public DataValidationException(List<Message> messages) {
        this.messages = messages != null ? messages.toArray(new Message[messages.size()]) : new Message[0];
    }

    public DataValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
