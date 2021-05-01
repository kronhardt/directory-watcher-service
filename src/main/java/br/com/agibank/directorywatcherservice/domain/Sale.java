package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Sale {
    private Long idSale;
    private List<Item> items;
    private String salesmanName;

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public List<Item> getItemsSale() {
        return items;
    }

    public void setItemsSale(List<Item> itemsSale) {
        this.items = itemsSale;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Double getItemsAmount() {
        return items.stream().mapToDouble(Item::getAmount).sum();
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Sale sales;

        private Builder() { sales = new Sale(); }

        public Builder idSale(Long idSale) {
            sales.setIdSale(idSale);
            return this;
        }

        public Builder itemsSale(List<Item> items) {
            sales.setItemsSale(items);
            return this;
        }

        public Builder salesmanName(String salesmanName) {
            sales.setSalesmanName(salesmanName);
            return this;
        }

        public Sale build() {
            return sales;
        }
    }
}