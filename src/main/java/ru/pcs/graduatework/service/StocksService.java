package ru.pcs.graduatework.service;

import org.springframework.data.jpa.repository.Query;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Stock;

import java.util.List;

public interface StocksService {
//    public List<PortfolioForm> getPortfolioInformation(Integer clientId);
    List<Stock> getAllStocks();

    Stock getStock(Integer stockId);

    List<PortfolioForm> getPortfolioInformation(Integer id);

    List<Stock> findAllByTicker(String ticker);

    Stock getById(Integer stockId);
}
