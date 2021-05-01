package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SalesReport {

    private final List<Salesman> salesmans;
    private final List<Customer> customers;
    private final List<Sale> sales;

    public SalesReport() {
        this.salesmans =  new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sales =  new ArrayList<>();
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
                .map(s -> s.getIdSale())
                .orElseThrow(NoSuchElementException::new);
    }

    public String getLowestSale() {
        return sales.stream()
                .min(Comparator.comparing(Sale::getItemsAmount))
                .map(s -> s.getSalesmanName())
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
}
