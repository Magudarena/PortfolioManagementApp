package com.CASH.CASH.service;
import com.CASH.CASH.model.Stock;
import com.CASH.CASH.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StockService {


    String baseURL = "https://api.twelvedata.com/price";

    String apiKey;

    StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {

        this.stockRepository = stockRepository;
        this.apiKey = System.getenv("api_key");
        System.out.println("api_key: " + apiKey);
    }


    public void deleteById(Long id){
        stockRepository.deleteById(id);
    }
    public void addStock(Stock stock) {
        Date currentDate = new Date();

        stock.setCreateTime(currentDate);
        stock.setUpdateTime(currentDate);

        stockRepository.save(stock);
    }
    public void deleteStock(Stock stock) {
        stockRepository.delete(stock);
    }
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }
    public BigDecimal getPrice(String symbol){

        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(this.baseURL)
                .queryParam("symbol", symbol)
                .queryParam("apikey", apiKey)
                .toUriString();

        System.out.println("url: " + url);

        Map<String, String> response = restTemplate.getForObject(url, Map.class);

        assert response != null;
        return new BigDecimal(response.get("price"));
    }


    public List<Stock> getStocks(){
        List<Stock> stocks = stockRepository.findAll();
        return stocks;
    }


}
