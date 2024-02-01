package com.example.customer.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer{
    @Id
    @SequenceGenerator(name = "mySeqGen", initialValue = 10000000)
    @GeneratedValue(generator = "mySeqGen")
    private Long customerNumber;

    @NotNull
    @Size(max= 50)
    private String customerName;

    @NotNull
    @Size(max= 20)
    private String customerMobile;

    @NotNull
    @Size(max= 50)
    @Email
    private String customerEmail;

    @NotNull
    @Size(max= 100)
    private String address1;

    @Size(max= 100)
    private String address2;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Long accountNumber;
    private Long availableBalance;
}




