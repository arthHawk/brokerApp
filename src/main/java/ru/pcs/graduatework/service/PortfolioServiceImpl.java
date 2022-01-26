package ru.pcs.graduatework.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.model.Stock;
import ru.pcs.graduatework.repositories.ClientsRepository;
import ru.pcs.graduatework.repositories.PortfoliosRepository;
import ru.pcs.graduatework.repositories.StocksRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfoliosRepository portfoliosRepository;
    private final StocksRepository stocksRepository;
    private final ClientsRepository clientsRepository;

    @Override
    public Boolean addStocks(Client client, Stock stock, Integer stocksCount) {
        if (BigDecimal.valueOf(stock.getQuote() * stocksCount).compareTo(client.getCash()) < 0) {
            client.setCash(client.getCash().subtract(BigDecimal.valueOf(stock.getQuote() * stocksCount)));
            if (portfolioExists(client.getId(), stock.getTicker())) {
                Integer portfolioId = searchPortfolioIdByClientIdAndTicker(client.getId(), stock.getTicker());
                Portfolio portfolio = getByID(portfolioId);
                portfolio.setCount(portfolio.getCount() + stocksCount);
                portfoliosRepository.save(portfolio);
                return true;
            } else {
                Portfolio portfolio = new Portfolio();
                portfolio.setClient(client);
                portfolio.setStock(stock);
                portfolio.setCount(stocksCount);
                portfoliosRepository.save(portfolio);
                return true;
            }
        } else { return false; }
    }

    public Boolean portfolioExists (Integer clientId, String ticker) {
        try {
            Integer portfolioId = searchPortfolioIdByClientIdAndTicker(clientId, ticker);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }


    @Override
    public Integer searchPortfolioIdByClientIdAndTicker(Integer clientId, String ticker) {
        List<Stock> stocks = stocksRepository.findAllByTicker(ticker);
        Stock stock = stocks.get(0);
        Client client = clientsRepository.getById(clientId);
        Portfolio portfolio = portfoliosRepository.getByClientAndStock(client, stock);

    return portfolio.getId();
    }

    @Override
    public Portfolio getByID(Integer portfolioId) {
        return portfoliosRepository.getById(portfolioId);
    }

    @Override
    public Boolean subtractStocks(Client client, Stock stock, Portfolio portfolio, Integer stocksCount) {
        if (stocksCount < portfolio.getCount()) {
            client.setCash(client.getCash().add(BigDecimal.valueOf(stock.getQuote() * stocksCount)));
            portfolio.setCount(portfolio.getCount() - stocksCount);
            portfoliosRepository.save(portfolio);
            return true;
        } else if (stocksCount == portfolio.getCount()) {
            client.setCash(client.getCash().add(BigDecimal.valueOf(stock.getQuote() * stocksCount)));
            portfoliosRepository.delete(portfolio);
            return true;
        }
        { return false; }
    }
}
