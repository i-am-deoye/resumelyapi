package com.resumly.resumeapi.core.exceptions;

import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;

public class InvalidPasswordException extends CustomException {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(new GlobalMessage("Incorrect password", "", Message.Severity.ERROR));
    }
}
