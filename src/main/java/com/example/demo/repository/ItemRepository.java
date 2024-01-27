package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    Optional<Item> findExisting(Specification<Item> specification);
    @Query("SELECT i FROM Item i " +
                   "WHERE i.name = ?1" +
                   "AND i.description = ?2" +
                     "AND i.unitPrice = ?3" +
                        "AND i.quantity = ?4")
    Optional<Item> findExisting(String name, String description, BigDecimal unitPrice, Integer quantity);
    Optional<Item> findByNameAndDescriptionAndUnitPriceAndQuantity(String name, String description, BigDecimal unitPrice, Integer quantity);
}