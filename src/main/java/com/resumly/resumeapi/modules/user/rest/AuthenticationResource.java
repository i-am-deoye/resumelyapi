package com.resumly.resumeapi.modules.user.rest;


import com.resumly.resumeapi.core.domain.DataResponse;
import com.resumly.resumeapi.core.domain.GlobalMessage;
import com.resumly.resumeapi.core.domain.IDataResponse;
import com.resumly.resumeapi.core.domain.Message;
import com.resumly.resumeapi.core.security.TokenProvider;
import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.user.dto.UserDTO;
import com.resumly.resumeapi.modules.user.rest.vm.AuthenticateVM;
import com.resumly.resumeapi.modules.user.rest.vm.LoginVM;
import com.resumly.resumeapi.modules.user.rest.vm.ManagedUserVM;
import com.resumly.resumeapi.modules.user.services.IUserService;
import com.resumly.resumeapi.modules.user.services.mappers.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationResource {

    private final IUserService userService;
    private final TokenProvider tokenProvider;
    private final UserMapper userMapper;

    public AuthenticationResource(IUserService userService, TokenProvider tokenProvider, UserMapper userMapper) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseBody
    DataResponse<AuthenticateVM> create(@RequestBody ManagedUserVM managedUserVM) {
        DataResponse<AuthenticateVM> response = new IDataResponse<>();
        try {
            UserDTO userDTO = userService.registerUser(managedUserVM, managedUserVM.getPassword());
            setAuthResponse(response, userDTO, managedUserVM.getPassword());
            response.addMessage(new GlobalMessage("User Created Successfully", "", Message.Severity.SUCCESS));
        } catch (CustomException e) {
            response.setMessages(Arrays.asList(e.getMessages()));
        }
        return response;
    }

    @PutMapping
    @ResponseBody
    DataResponse<?> update(@RequestBody ManagedUserVM managedUserVM) {
        DataResponse<?> response = new IDataResponse<>();
        try {
            userService.updateUser(managedUserVM);
            response.setValid(true);
            response.addMessage(new GlobalMessage("User Updated Successfully", "", Message.Severity.SUCCESS));
        } catch (CustomException e) {
            response.setMessages(Arrays.asList(e.getMessages()));
        }
        return response;
    }


    @PostMapping("/auth")
    @ResponseBody
    DataResponse<AuthenticateVM> login(@RequestBody LoginVM loginVM) {
        DataResponse<AuthenticateVM> response = new IDataResponse<>();

        try {
            UserDTO userDTO = userService.authenticateUser(loginVM);
            setAuthResponse(response, userDTO, loginVM.getPassword());
            response.addMessage(new GlobalMessage("User Login Successfully", "", Message.Severity.SUCCESS));
        } catch (CustomException e) {
            response.setMessages(Arrays.asList(e.getMessages()));
        }

        return response;
    }


    private void setAuthResponse(DataResponse<AuthenticateVM> response, UserDTO userDTO, String password) {
        response.setValid(true);
        AuthenticateVM authenticateVM = new AuthenticateVM();
        authenticateVM.setUser(userDTO);
        setToken(authenticateVM, userDTO, password);
        response.addData(authenticateVM);
    }

    private void setToken(AuthenticateVM authenticateVM, UserDTO userDTO, String password) {
        authenticateVM.setAccessToken(tokenProvider.generateToken(userMapper.toEntity(userDTO), password));
    }
}
