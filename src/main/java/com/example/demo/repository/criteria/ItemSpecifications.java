package com.example.demo.repository.criteria;

import com.example.demo.model.Item;
import com.example.demo.model.ui.ItemExtendedDto;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ItemSpecifications {
    public static Specification<Item> withName(String name) {
        return (root, query, criteriaBuilder) ->
                       name != null ? criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), name.toLowerCase())
                               : null;
    }
    
    public static Specification<Item> withDescription(String description) {
        return (root, query, criteriaBuilder) ->
                       description != null ? criteriaBuilder.equal(criteriaBuilder.lower(root.get("description")), description.toLowerCase())
                               : null;
    }
    
    public static Specification<Item> withUnitPrice(BigDecimal unitPrice) {
        return (root, query, criteriaBuilder) ->
                       unitPrice != null ? criteriaBuilder.equal(root.get("unitPrice"), unitPrice) : null;
    }
    
    public static Specification<Item> withQuantity(Integer quantity) {
        return (root, query, criteriaBuilder) ->
                       quantity != null ? criteriaBuilder.equal(root.get("quantity"), quantity) : null;
    }
    
    public static Specification<Item> build() {
        return Specification.where(null);
    }
    
    public static Specification<Item> getExistingSpecification(ItemExtendedDto item) {
        return Specification.where(withName(item.getName()))
                       .and(withDescription(item.getDescription()))
                       .and(withUnitPrice(item.getUnitPrice()))
                       .and(withQuantity(item.getQuantity()));
    }
}
