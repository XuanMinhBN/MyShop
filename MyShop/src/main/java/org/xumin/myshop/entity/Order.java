package org.xumin.myshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.xumin.myshop.util.FormatOrderNumber;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_shipped_date")
    private Date orderShipDate;

    @Column(name = "order_created_at")
    private Date orderCreatedAt;

    @Column(name = "order_discount")
    private Double orderDiscount;

    @Column(name = "order_total_amount")
    private Double orderTotalAmount;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_mobile")
    private String receiverMobile;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetail;

    public String getOrderNumber(){
        return FormatOrderNumber.LPAD(String.valueOf(id), 5, "0");
    }
}
