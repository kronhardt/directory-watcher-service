package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

@Component
public class Customer {
    private String cnpj;
    private String name;
    private String area;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Customer customer;

        private Builder() { customer = new Customer(); }

        public Builder cnpj(String cnpj) {
            customer.setCnpj(cnpj);
            return this;
        }

        public Builder name(String name) {
            customer.setName(name);
            return this;
        }

        public Builder area(String area) {
            customer.setArea(area);
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}
