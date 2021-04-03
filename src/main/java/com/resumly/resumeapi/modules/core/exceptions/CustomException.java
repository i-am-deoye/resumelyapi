package com.resumly.resumeapi.modules.core.exceptions;

import com.resumly.resumeapi.core.domain.Message;

public class CustomException extends Exception {

    protected Message[] messages;

    public CustomException() {
        super();

    }


    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomException(Message...message) {
        this.messages = message;
    }

    public Message[] getMessages() {
        return this.messages;
    }

}
