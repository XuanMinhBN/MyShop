package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personal_register")
public class PersonalRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_register_id", nullable = false)
    private int id;

    @Column(name = "personal_register_email", nullable = false)
    private String personalRegisterEmail;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_register_password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id")
    private Login login;
}
