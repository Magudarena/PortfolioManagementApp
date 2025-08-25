package com.CASH.CASH.controller;

import com.CASH.CASH.model.Stock;
import com.CASH.CASH.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/list")
    public String listStocks(Model model) {
        model.addAttribute("stocks", stockService.getStocks());
        return "stock-list";
    }

    @GetMapping("/new")
    public String showAddStockForm(Model model) {
        model.addAttribute("stock", new Stock());
        return "stock-form";
    }

    @PostMapping
    public String addStock(@ModelAttribute Stock stock, Model model) {

        stock.setPrice(this.stockService.getPrice(stock.getShortName()));

        stockService.addStock(stock);

        return "redirect:/stocks/list";
    }
}
