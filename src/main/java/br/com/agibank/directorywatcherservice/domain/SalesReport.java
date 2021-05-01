package br.com.agibank.directorywatcherservice.domain;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class SalesReport {

    private final List<Salesman> salesmans = new LinkedList<>();
    private final List<Customer> customers = new LinkedList<>();
    private final List<Sale> sales = new LinkedList<>();

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

    public Long getLowestSale() {
        return sales.stream()
                .min(Comparator.comparing(Sale::getItemsAmount))
                .map(s -> s.getIdSale())
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
