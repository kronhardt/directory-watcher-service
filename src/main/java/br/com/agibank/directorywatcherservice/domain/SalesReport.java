package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SalesReport {

    private List<Salesman> salesmans;
    private List<Customer> customers;
    private List<Sale> sales;

    public SalesReport() {
        this.salesmans =  new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sales =  new ArrayList<>();
    }

    public List<Salesman> getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(List<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Integer countCustomer(){
        return customers.size();
    }

    public Integer countSalesman(){
        return salesmans.size();
    }

    public Long getMostExpansiveSale() {
        return sales.stream()
                .max(Comparator.comparing(Sale::getItemsAmount))
                .map(Sale::getIdSale)
                .orElseThrow(NoSuchElementException::new);
    }

    public String getLowestSale() {
        return sales.stream()
                .min(Comparator.comparing(Sale::getItemsAmount))
                .map(Sale::getSalesmanName)
                .orElseThrow(NoSuchElementException::new);
    }

    public void addData(Object data) {
        if (data instanceof Salesman) {
            salesmans.add((Salesman) data);
        } else if(data instanceof Customer) {
            customers.add((Customer) data);
        } else {
            sales.add((Sale) data);
        }
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private SalesReport salesReport;

        private Builder() { salesReport = new SalesReport(); }

        public Builder salesmans(List<Salesman> salesmans) {
            salesReport.setSalesmans(salesmans);
            return this;
        }

        public Builder customers(List<Customer> customers) {
            salesReport.setCustomers(customers);
            return this;
        }

        public Builder sales(List<Sale> sales) {
            salesReport.setSales(sales);
            return this;
        }

        public SalesReport build() { return salesReport; }

    }
}
