package ru.pcs.graduatework.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.StockDto;
import ru.pcs.graduatework.entities.ClientEntity;
import ru.pcs.graduatework.entities.PortfolioEntity;
import ru.pcs.graduatework.entities.StockEntity;
import ru.pcs.graduatework.repositories.ClientsRepository;
import ru.pcs.graduatework.repositories.PortfoliosRepository;
import ru.pcs.graduatework.repositories.StocksRepository;
import ru.pcs.graduatework.service.PortfolioService;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfoliosRepository portfoliosRepository;
    private final StocksRepository stocksRepository;
    private final ClientsRepository clientsRepository;

    @Override
    public Boolean addStocks(ClientDto clientDto, StockDto stockDto, Integer stocksCount) {
        if (BigDecimal.valueOf(stockDto.getQuote() * stocksCount).compareTo(clientDto.getCash()) < 0) {

            ClientEntity clientEntity = clientsRepository.getById(clientDto.getId());
            StockEntity stockEntity = stocksRepository.getById(stockDto.getId());

            clientEntity.setCash(clientDto.getCash().subtract(BigDecimal.valueOf(stockDto.getQuote() * stocksCount)));

            if (portfolioExists(clientDto.getId(), stockDto.getTicker())) {
                Integer portfolioId = searchPortfolioIdByClientIdAndTicker(clientDto.getId(), stockDto.getTicker());
                PortfolioEntity portfolioEntity = getByID(portfolioId);
                portfolioEntity.setCount(portfolioEntity.getCount() + stocksCount);
                portfoliosRepository.save(portfolioEntity);
                return true;
            } else {
                PortfolioEntity portfolioEntity = new PortfolioEntity();
                portfolioEntity.setClientEntity(clientEntity);
                portfolioEntity.setStockEntity(stockEntity);
                portfolioEntity.setCount(stocksCount);
                portfoliosRepository.save(portfolioEntity);
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean portfolioExists(Integer clientId, String ticker) {
        try {
            Integer portfolioId = searchPortfolioIdByClientIdAndTicker(clientId, ticker);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }


    @Override
    public Integer searchPortfolioIdByClientIdAndTicker(Integer clientId, String ticker) {
        List<StockEntity> stockEntities = stocksRepository.findAllByTicker(ticker);
        StockEntity stockEntity = stockEntities.get(0);
        ClientEntity clientEntity = clientsRepository.getById(clientId);
        PortfolioEntity portfolioEntity = portfoliosRepository.getByClientEntityAndStockEntity(clientEntity, stockEntity);

        return portfolioEntity.getId();
    }

    @Override
    public PortfolioEntity getByID(Integer portfolioId) {
        return portfoliosRepository.getById(portfolioId);
    }

    @Override
    public Boolean subtractStocks(ClientDto clientDto, StockDto stockDto, PortfolioEntity portfolioEntity, Integer stocksCount) {

        ClientEntity clientEntity = clientsRepository.getById(clientDto.getId());
        StockEntity stockEntity = stocksRepository.getById(stockDto.getId());

        if (stocksCount < portfolioEntity.getCount()) {
            clientEntity.setCash(clientEntity.getCash().add(BigDecimal.valueOf(stockEntity.getQuote() * stocksCount)));
            portfolioEntity.setCount(portfolioEntity.getCount() - stocksCount);
            portfoliosRepository.save(portfolioEntity);
            return true;
        } else if (stocksCount == portfolioEntity.getCount()) {
            clientEntity.setCash(clientEntity.getCash().add(BigDecimal.valueOf(stockEntity.getQuote() * stocksCount)));
            portfoliosRepository.delete(portfolioEntity);
            return true;
        }
        {
            return false;
        }
    }
}
