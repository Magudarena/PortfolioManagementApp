package com.CASH.CASH.controller;

import com.CASH.CASH.model.Stock;
import com.CASH.CASH.service.StockService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    StockService stockService;


    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id){
        this.stockService.deleteById(id);
    }

    @PostMapping
    public void addStock(@RequestBody Stock stock){
        this.stockService.addStock(stock);
    }
    @PutMapping
    public void updateStock(@RequestBody Stock stock){
        this.stockService.updateStock(stock);
    }

    @GetMapping
    public List<Stock> getStocks(){
        return this.stockService.getStocks();
    }

}
