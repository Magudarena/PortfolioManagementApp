package com.CASH.CASH.controller;

import com.CASH.CASH.model.Stock;
import com.CASH.CASH.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/stocks")
public class StockController {

    StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping()
    public String addStock(@ModelAttribute Stock stock){

        stock.setCreateTime(new Date());
        stock.setUpdateTime(new Date());
        this.stockService.addStock(stock);

        return "index";
    }


    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id){
        this.stockService.deleteById(id);
    }


    @PutMapping
    public void updateStock(@RequestBody Stock stock){
        this.stockService.updateStock(stock);
    }

    @GetMapping("/list")
    public String getStocks(Model model){

        model.addAttribute("stocks", this.stockService.getStocks());
        return "stock_list";
    }

}
