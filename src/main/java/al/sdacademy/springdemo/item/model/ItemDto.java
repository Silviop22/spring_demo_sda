package al.sdacademy.springdemo.item.model;

import java.math.BigDecimal;

public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
    private String imageUrl;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal unitPrice;
        private Integer quantity;
        private String imageUrl;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }
        
        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }
        
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public ItemDto build() {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(id);
            itemDto.setName(name);
            itemDto.setDescription(description);
            itemDto.setUnitPrice(unitPrice);
            itemDto.setQuantity(quantity);
            itemDto.setImageUrl(imageUrl);
            return itemDto;
        }
    }
}
