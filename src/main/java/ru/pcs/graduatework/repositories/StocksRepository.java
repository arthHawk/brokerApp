package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.entities.StockEntity;

import java.util.List;

public interface StocksRepository extends JpaRepository<StockEntity, Integer> {

    List<StockEntity> findAllByTicker(String ticker);

    @Query("SELECT new ru.pcs.graduatework.dto.ClientPortfolioDto (p.clientEntity.id, s.issuer, s.ticker, s.quote, p.count) " +
            "from StockEntity s " +
            "inner join PortfolioEntity p on s.id = p.stockEntity.id " +
            "and p.clientEntity.id = ?1")
    public List<ClientPortfolioDto> getPortfolioInformation(Integer clientId);


    // https://thorben-janssen.com/spring-data-jpa-query-projections/
//    List<Stock> getAllStocks();

//    List<Stock> getAllById(Integer id);
}
