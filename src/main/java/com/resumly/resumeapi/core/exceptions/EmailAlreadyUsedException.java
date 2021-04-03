package com.resumly.resumeapi.core.exceptions;

import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;

public class EmailAlreadyUsedException extends CustomException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super(new GlobalMessage("Email is already in use!", "", Message.Severity.ERROR));
    }
}
