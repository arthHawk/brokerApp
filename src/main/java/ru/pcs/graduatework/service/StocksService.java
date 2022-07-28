package ru.pcs.graduatework.service;

import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.dto.StockDto;

import java.util.List;

public interface StocksService {
    //    public List<PortfolioForm> getPortfolioInformation(Integer clientId);
    List<StockDto> getAllStocks();

    StockDto getStock(Integer stockId);

    List<ClientPortfolioDto> getPortfolioInformation(Integer id);

    List<StockDto> findAllByTicker(String ticker);

    StockDto getById(Integer stockId);
}
