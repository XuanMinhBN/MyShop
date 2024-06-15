package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[user_address]")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_address_id")
    private Long id;
    @Column(name = "user_address_name")
    private String address;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_mobile")
    private String receiverMobile;
    @Column(name = "default_address")
    private boolean defaultAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
