package ru.pcs.graduatework.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.dto.DtoFactory;
import ru.pcs.graduatework.dto.StockDto;
import ru.pcs.graduatework.entities.StockEntity;
import ru.pcs.graduatework.repositories.StocksRepository;
import ru.pcs.graduatework.service.StocksService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;

    @Override
    public List<StockDto> getAllStocks() {
        List<StockEntity> stockEntityList = stocksRepository.findAll();
        return stockEntityList.stream()
                .map(DtoFactory::createStockDto)
                .collect(Collectors.toList());

    }

    @Override
    public StockDto getStock(Integer stockId) {
        StockEntity stockEntity = stocksRepository.getById(stockId);
        return DtoFactory.createStockDto(stockEntity);
    }

    @Override
    public List<ClientPortfolioDto> getPortfolioInformation(Integer id) {
        return stocksRepository.getPortfolioInformation(id);
    }

    @Override
    public List<StockDto> findAllByTicker(String ticker) {
        List<StockEntity> stockEntityList = stocksRepository.findAllByTicker(ticker);

        return stockEntityList.stream()
                .map(DtoFactory::createStockDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto getById(Integer stockId) {
        StockEntity stockEntity = stocksRepository.getById(stockId);

        return DtoFactory.createStockDto(stockEntity);
    }

}
