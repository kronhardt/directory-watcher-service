package br.com.agibank.directorywatcherservice.stub;

import br.com.agibank.directorywatcherservice.domain.Customer;

public class CustomerStub {

    public static Customer createCustomerFelipe() {
        return Customer.builder()
                .name("Felipe")
                .cnpj("1231231234564")
                .area("Crm")
                .build();
    }

    public static Customer createCustomerMarcus() {
        return Customer.builder()
                .name("Marcus")
                .cnpj("1231231234564")
                .area("Contabilidade")
                .build();
    }
}
