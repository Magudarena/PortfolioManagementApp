package com.CASH.CASH.controller;

import com.CASH.CASH.model.Portfolio;
import com.CASH.CASH.service.PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/new")
    public String showCreatePortfolioForm(Model model) {
        model.addAttribute("portfolio", new Portfolio());
        return "portfolio-form";
    }

    @PostMapping
    public String createPortfolio(@ModelAttribute Portfolio portfolio) {
        portfolioService.createPortfolio(portfolio);
        return "redirect:/portfolios/list";
    }

    @GetMapping("/list")
    public String listPortfolios(Model model) {
        model.addAttribute("portfolios", portfolioService.getAllPortfolios());
        return "portfolio-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Portfolio p = portfolioService.getById(id);
        model.addAttribute("portfolio", p);
        return "portfolio-form";
    }

    @PostMapping("/edit/{id}")
    public String updatePortfolio(@PathVariable Long id,
                                  @RequestParam("portfolioName") String portfolioName) {
        portfolioService.updatePortfolioName(id, portfolioName);
        return "redirect:/portfolios/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePortfolio(@PathVariable Long id) {
        portfolioService.deleteById(id);
        return "redirect:/portfolios/list";
    }

}
