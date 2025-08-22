package com.CASH.CASH.repository;

import com.CASH.CASH.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
