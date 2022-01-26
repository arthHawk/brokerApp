package ru.pcs.graduatework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Stock;
import ru.pcs.graduatework.repositories.StocksRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;

    @Override
    public List<Stock> getAllStocks() {
        return stocksRepository.findAll();
    }

    @Override
    public Stock getStock(Integer stockId) {
        return stocksRepository.getById(stockId);
    }

    @Override
    public List<PortfolioForm> getPortfolioInformation(Integer id) {
        return stocksRepository.getPortfolioInformation(id);
    }

    @Override
    public List<Stock> findAllByTicker(String ticker) {
        return stocksRepository.findAllByTicker(ticker);
    }

    @Override
    public Stock getById(Integer stockId) {
        return stocksRepository.getById(stockId);
    }

}
