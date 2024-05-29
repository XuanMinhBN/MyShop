package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id", nullable = false)
    private int id;

    @Column(name = "login_email", nullable = false)
    private String loginEmail;

    @Column(name = "login_password", nullable = false)
    private String loginPassword;

    @OneToOne(mappedBy = "login",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonalRegister personalRegister;

    @OneToOne(mappedBy = "login",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BusinessRegister businessRegister;
}
