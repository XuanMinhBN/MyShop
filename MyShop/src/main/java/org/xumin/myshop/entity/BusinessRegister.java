package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_register")
public class BusinessRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_register_id", nullable = false)
    private int id;

    @Column(name = "business_register_email", nullable = false)
    private String businessRegisterEmail;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "personal_register_password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id")
    private Login login;
}
