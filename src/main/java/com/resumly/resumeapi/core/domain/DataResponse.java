package com.resumly.resumeapi.core.domain;

import java.util.List;

public interface DataResponse<M> {

    boolean isValid();

    void setValid(boolean valid);

    List<M> getData();

    void setData(List<M> data);
    void addData(M dataEntry);

    List<Message> getMessages();

    void setMessages(List<Message> messages);
    void addMessage(Message msg);
    void addMessage(String msg);
    void addMessage(String msg, Message.Severity severity);
    void addMessage(String msg, String detail, Message.Severity severity);

}
