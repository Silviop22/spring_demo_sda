package al.sdacademy.springdemo.item.repository;

import al.sdacademy.springdemo.item.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);
    
    Optional<Item> findByName(String name);
}
