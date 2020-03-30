package com.getartur.billingcore.shared.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "company")
@Data
public class CompanyProperties {

    private String name;
    private String owner;
    private String vatin;
    private String eMail;
    private String mobile;

    private Address address;
    private BankAccount bankAccount;

    @Data
    public static class Address {
        private String line1;
        private String line2;
        private String zip;
        private String city;
        private String country;
    }

    @Data
    public static class BankAccount {
        private String name;
        private String iban;
        private String bic;
    }
}


