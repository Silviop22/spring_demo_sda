package al.sdacademy.springdemo.user.model;

import al.sdacademy.springdemo.order.model.Order;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private boolean active;
    private UserRole role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role.name());
    }
    
    @Override
    public String getUsername() {
        return this.email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    
    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
