package com.resumly.resumeapi.modules.user.rest.vm;

import com.resumly.resumeapi.modules.user.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;


    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }


    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
