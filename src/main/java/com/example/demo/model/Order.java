package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float totalPrice;
    private String shippingAddress;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_items",
    joinColumns = { @JoinColumn(name = "order_id") },
                      inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Item> items;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    
    
}
