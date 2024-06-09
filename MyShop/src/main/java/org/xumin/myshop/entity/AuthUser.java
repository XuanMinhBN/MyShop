package org.xumin.myshop.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

public class AuthUser extends User {

    private Long id;
    private Date createdAt;
    private boolean deleteStatus;
    private boolean status;

    public AuthUser(String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities,
                    Long id,
                    Date createdAt,
                    boolean deleteStatus,
                    boolean status) {
        super(username, password, authorities);
        this.id = id;
        this.createdAt = createdAt;
        this.deleteStatus = deleteStatus;
        this.status = status;
    }

}
