package com.resumly.resumeapi.modules.user.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity(name = "authority")
public class Authority implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @NotNull
    @Size(max = 50)
    @Column
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return Objects.equals(name, ((Authority) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                "}";
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
