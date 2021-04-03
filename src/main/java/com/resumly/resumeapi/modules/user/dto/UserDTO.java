package com.resumly.resumeapi.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.resumly.resumeapi.modules.user.domain.Authority;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO implements Serializable {

    private Long id;

    @NotBlank
    //@Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    private String langKey;

    private Long createdBy;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;

    private String lastModifiedBy;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastModifiedDate;

    private Set<Authority> authorities;


    public UserDTO() {
        // Empty constructor needed for Jackson.
    }



    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", activated=" + activated +
                ", langKey='" + langKey + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", authorities=" + authorities +
                "}";
    }
}
