package com.resumly.resumeapi.core.exceptions;

import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;

public class EmailNotFoundException extends CustomException {

    private static final long serialVersionUID = 1L;

    public EmailNotFoundException() {
        super(new GlobalMessage("Email address not registered", "", Message.Severity.ERROR));
    }
}
