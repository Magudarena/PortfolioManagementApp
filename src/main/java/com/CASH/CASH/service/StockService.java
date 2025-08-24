package com.CASH.CASH.service;
import com.CASH.CASH.model.Stock;
import com.CASH.CASH.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class StockService {

//    @Value("${data.api.key}")
//    String api_key;

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

//    public Stock getStockData(String name){
//
//        String url = "https://api.twelvedata.com/endpoint?symbol=" + name + "&apikey=" + api_key;
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        return restTemplate.getForObject(url, Stock.class);
//    }

    public List<Stock> getStocks(){
        List<Stock> stocks = stockRepository.findAll();
        return stocks;
    }


}
