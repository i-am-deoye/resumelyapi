package com.resumly.resumeapi.modules.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resumly.resumeapi.core.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "profile")
public class User extends BaseEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @NotNull
    //@Pattern(regexp = Constant.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column
    private String login;

    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @Size(max = 50)
    @Column
    private String firstName;

    @Size(max = 50)
    @Column
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Column
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 10)
    @Column
    private String langKey;

    @Size(max = 256)
    @Column
    private String imageUrl;

    @Size(max = 20)
    @Column
    private String activationKey;

    @Size(max = 20)
    @Column
    private String resetKey;

    @Column
    private Date resetDate = null;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Authority.class)
    @JsonIgnore
    private Set authorities = new HashSet<>();





    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login.toLowerCase();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return getId() == (((User) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", activated='" + activated + '\'' +
                ", langKey='" + langKey + '\'' +
                ", activationKey='" + activationKey + '\'' +
                "}";
    }
}
