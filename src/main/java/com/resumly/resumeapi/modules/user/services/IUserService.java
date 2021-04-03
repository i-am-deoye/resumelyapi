package com.resumly.resumeapi.modules.user.services;

import com.resumly.resumeapi.modules.core.exceptions.CustomException;
import com.resumly.resumeapi.modules.user.dto.UserDTO;
import com.resumly.resumeapi.modules.user.rest.vm.LoginVM;

public interface IUserService {
    UserDTO authenticateUser(LoginVM loginVM) throws CustomException;
    UserDTO registerUser(UserDTO userDTO, String password) throws CustomException;
    void updateUser(UserDTO userDTO) throws CustomException;
}
