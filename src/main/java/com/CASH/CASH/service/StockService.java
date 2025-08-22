package com.CASH.CASH.service;


import com.CASH.CASH.model.Stock;
import com.CASH.CASH.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;

    }
    public void deleteById(Long id){
        stockRepository.deleteById(id);
    }

    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }
    public void deleteStock(Stock stock) {
        stockRepository.delete(stock);
    }
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }
    public List<Stock> getStocks(){
        List<Stock> stocks = stockRepository.findAll();
        return stocks;
    }


}
