package com.CASH.CASH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock extends Asset{

//    @Column(nullable = false, length = 50)
//    String companyName;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    Portfolio portfolio;


}
