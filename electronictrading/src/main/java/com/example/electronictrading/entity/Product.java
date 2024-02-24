package com.example.electronictrading.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name for product can't be null")
    @Column(name="name")
    private  String name;

    @NotNull(message = "brand for product can't be null")
    @Column(name="brand")
    private  String brand;

    @NotNull(message = "price for product can't be null")
    @Column(name="price")
    private BigDecimal price;

    @NotNull(message = "stock for product can't be null")
    @Column(name="stock")
    private Integer stock;

    @NotNull(message = "addind date for product can't be null")
    @Column(name="adding_date")
    private LocalDateTime addingDate;
}
