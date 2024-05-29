package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String fullName;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column
    private boolean deleteStatus;
    @Column
    private boolean status;

    @OneToOne(mappedBy = "theUser", cascade = CascadeType.ALL)
    private UserDetail userDetail;
    @OneToMany(mappedBy = "theUser", cascade = CascadeType.ALL)
    private List<ShippingAddress> shippingAddresses;
}
