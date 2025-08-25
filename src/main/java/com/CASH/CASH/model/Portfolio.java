package com.CASH.CASH.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String portfolioName;

    @OneToMany(mappedBy = "portfolio")
    private List<Stock> stockList;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPortfolioName() { return portfolioName; }
    public void setPortfolioName(String portfolioName) { this.portfolioName = portfolioName; }

    public List<Stock> getStockList() { return stockList; }
    public void setStockList(List<Stock> stockList) { this.stockList = stockList; }
}
