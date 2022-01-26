package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Stock;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findAllByTicker(String ticker);

    @Query("SELECT new ru.pcs.graduatework.forms.PortfolioForm(p.client.id, s.issuer, s.ticker, s.quote, p.count) " +
            "from Stock s " +
            "inner join Portfolio p on s.id = p.stock.id " +
            "and p.client.id = ?1")
    public List<PortfolioForm> getPortfolioInformation(Integer clientId);


//    List<Stock> getAllStocks();

//    List<Stock> getAllById(Integer id);
}
