package ru.pcs.graduatework.service;

import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.StockDto;
import ru.pcs.graduatework.entities.PortfolioEntity;

public interface PortfolioService {
    public Boolean addStocks(ClientDto clientDto, StockDto stockDto, Integer stocksCount);

    Integer searchPortfolioIdByClientIdAndTicker(Integer clientId, String ticker);

    PortfolioEntity getByID(Integer portfolioId);

    Boolean subtractStocks(ClientDto clientDto, StockDto stockDto, PortfolioEntity portfolioEntity, Integer stocksCount);
}
