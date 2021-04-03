package com.resumly.resumeapi.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class IDataResponse<M> implements DataResponse<M> {

    private boolean valid;

    private List<M> data;

    private List<Message> messages;

    public IDataResponse() {
        valid = false;
    }

    public IDataResponse(boolean valid, List<M> data, List<Message> messages) {
        this.valid = valid;
        this.data = data;
        this.messages = messages;
    }

    public IDataResponse(boolean valid) {
        this.valid = valid;
    }

    @JsonProperty("responseCode")
    public String getResponseCode() {
        return valid ? ResponseCode.SUCCESSFUL.getCode() : ResponseCode.DEFAULT_SERVER_ERROR.getCode();
    }

    @Override
    public List<M> getData() {
        return data;
    }

    @Override
    public void addMessage(String msg, Message.Severity severity) {
        addMessage(msg, null, severity);
    }


    @Override
    public void addMessage(String msg, String detail, Message.Severity severity) {
        if (msg != null || detail != null) {
            addMessage(new GlobalMessage(msg, detail, severity));
        }
    }


    @Override
    public void setData(List<M> data) {
        this.data = data;
    }

    @Override
    public void addData(M dataEntry) {
        if (dataEntry != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            this.data.add(dataEntry);
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void addMessage(Message msg) {
        if (msg != null) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }
            this.messages.add(msg);
        }
    }


    @Override
    public void addMessage(String msg) {
        addMessage(msg, null);
    }

    public boolean hasMessages() {
        return this.messages != null && !this.messages.isEmpty();
    }
}
