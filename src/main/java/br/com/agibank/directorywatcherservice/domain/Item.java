package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

@Component
public class Item {
    private Long idItem;
    private Double quantity;
    private Double price;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount(){
        return quantity * price;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Item item;

        private Builder() { item = new Item(); }

        public Builder idItem(Long idItem) {
            item.setIdItem(idItem);
            return this;
        }

        public Builder quantity(Double quantity) {
            item.setQuantity(quantity);
            return this;
        }

        public Builder price(Double price) {
            item.setPrice(price);
            return this;
        }

        public Item build() {
            return item;
        }
    }
}
