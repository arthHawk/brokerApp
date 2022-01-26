package ru.pcs.graduatework.service;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.model.Stock;

public interface PortfolioService {
    public Boolean addStocks(Client client, Stock stock, Integer stocksCount);

    Integer searchPortfolioIdByClientIdAndTicker(Integer clientId, String ticker);

    Portfolio getByID(Integer portfolioId);

    Boolean subtractStocks(Client client, Stock stock, Portfolio portfolio, Integer stocksCount);
}
