package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders")
    @SequenceGenerator(
            name = "seq_orders", allocationSize = 1
    )
    private Long id;
    private String orderNumber;
    @OneToMany(mappedBy = "order")
    private List<OrderLineItems>orderLineItems;

}
