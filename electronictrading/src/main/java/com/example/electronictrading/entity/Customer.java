package com.example.electronictrading.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name for customer can't be null")
    @Column(name="first_name")
    private  String firstName;

    @NotNull(message = "surname for customer can't be null")
    @Column(name="last_name")
    private  String lastName;

    @NotNull(message = "e-mail for customer can't be null")
    @Column(name="e_mail")
    private  String eMail;

    @NotNull(message = "phone number for customer can't be null")
    @Column(name="phone_number")
    private  String phoneNumber;

    @NotNull(message = "address for customer  can't be null")
    @Column(name="address")
    private  String address;

    @NotNull(message = "city for customer can't be null")
    @Column(name="city")
    private  String city;

    @NotNull(message = "subscribe date for customer can't be null")
    @Column(name="subscribe_date")
    private LocalDateTime subscribeDate;

}
