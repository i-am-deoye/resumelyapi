package com.resumly.resumeapi.modules.user.rest.vm;

import com.resumly.resumeapi.modules.user.dto.UserDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticateVM implements Serializable {
    private UserDTO user;
    private String accessToken;
    private String refreshToken;
}
