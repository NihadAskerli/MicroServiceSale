package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders")
    @SequenceGenerator(
            name = "seq_orders", allocationSize = 1
    )
    private Long id;
    private String barCode;
    private BigDecimal price;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
}
