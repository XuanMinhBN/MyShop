package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "delete_status")
    private boolean deleteStatus;
    @Column
    private boolean status;

    @OneToOne(mappedBy = "theUser", cascade = CascadeType.ALL)
    private UserDetail userDetail;
    @OneToMany(mappedBy = "theUser", cascade = CascadeType.ALL)
    private List<ShippingAddress> shippingAddresses;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "role_id")}
    )
    private Set<Role> roles;

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
