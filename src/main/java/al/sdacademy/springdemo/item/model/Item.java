package al.sdacademy.springdemo.item.model;

import al.sdacademy.springdemo.order.model.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "items")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    
    @Column(nullable = false)
    private Integer quantity;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    @ManyToMany(mappedBy = "items")
    private Set<Order> orders;
}
