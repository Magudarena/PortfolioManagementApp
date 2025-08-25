package com.CASH.CASH.service;

import com.CASH.CASH.model.Portfolio;
import com.CASH.CASH.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    public Portfolio getById(Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with id: " + id));
    }

    public Portfolio updatePortfolioName(Long id, String portfolioName) {
        Portfolio portfolio = getById(id);
        portfolio.setPortfolioName(portfolioName);
        return portfolioRepository.save(portfolio);
    }

    public void deleteById(Long id) {
        if (!portfolioRepository.existsById(id)) {
            throw new IllegalArgumentException("Portfolio not found with id: " + id);
        }
        portfolioRepository.deleteById(id);
    }
}
