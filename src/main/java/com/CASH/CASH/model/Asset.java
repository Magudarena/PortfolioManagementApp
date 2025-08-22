package com.CASH.CASH.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Asset {


    @Column(name = "short_name", nullable = false, length = 50, unique = true)
    String shortName;
    @Column(name = "stock_name", nullable = false, length = 50, unique = true)
    String name;
    @Column(name = "stock_price", nullable = false, precision = 10, scale = 2)
    BigDecimal price;
    @Column(name = "stock_market_cap", nullable = false, precision = 10, scale = 2)
    BigDecimal marketCap;
    @Column(name = "create_time", nullable = false)
    Date createTime;
    @Column(name = "update_time", nullable = false)
    Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




}
