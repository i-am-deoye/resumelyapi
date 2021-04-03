package com.resumly.resumeapi.core.exceptions;

import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;

public class LoginAlreadyUsedException extends CustomException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(new GlobalMessage("Login name already used!", "", Message.Severity.ERROR));
    }
}
