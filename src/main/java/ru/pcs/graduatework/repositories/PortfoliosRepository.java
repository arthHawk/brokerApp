package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.model.Stock;

import java.util.List;

public interface PortfoliosRepository extends JpaRepository<Portfolio, Integer> {
    List<Portfolio> findAllByClient_Id(Integer id);
    List<Portfolio> findAllByClientIsNull();

    Portfolio getByClientAndStock(Client client, Stock stock);

//    @Override
//    Portfolio getById(Integer integer);
//
//    @Query("SELECT id from Portfolio where id = ?1")
//    Portfolio getPortfolioByID(Integer portfolioId);

}
