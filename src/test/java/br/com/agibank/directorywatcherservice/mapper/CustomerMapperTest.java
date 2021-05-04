package br.com.agibank.directorywatcherservice.mapper;


import br.com.agibank.directorywatcherservice.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapperTest {

    @Test
    void mustBeSuccessfullyMapCustomerData() {

        Customer customerExpected = Customer.builder()
                .name("Felipe")
                .cnpj("1231231234564")
                .area("Crm")
                .build();

        List<String> lineCustomer = new ArrayList<>();
        lineCustomer.add("1231231234564");
        lineCustomer.add("Felipe");
        lineCustomer.add("Crm");

        CustomerMapper customerMapper = new CustomerMapper();
        Customer customerActual = customerMapper.mapFrom(lineCustomer);

        Assertions.assertEquals(customerExpected.getCnpj(), customerActual.getCnpj());
        Assertions.assertEquals(customerExpected.getName(), customerActual.getName());
        Assertions.assertEquals(customerExpected.getArea(), customerActual.getArea());
    }
}
