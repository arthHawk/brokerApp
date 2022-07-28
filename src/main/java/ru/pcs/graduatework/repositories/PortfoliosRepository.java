package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.graduatework.entities.ClientEntity;
import ru.pcs.graduatework.entities.PortfolioEntity;
import ru.pcs.graduatework.entities.StockEntity;

import java.util.List;

public interface PortfoliosRepository extends JpaRepository<PortfolioEntity, Integer> {
    List<PortfolioEntity> findAllByClientEntity_Id(Integer id);

    List<PortfolioEntity> findAllByClientEntityIsNull();

    PortfolioEntity getByClientEntityAndStockEntity(ClientEntity clientEntity, StockEntity stockEntity);

//    @Override
//    Portfolio getById(Integer integer);
//
//    @Query("SELECT id from Portfolio where id = ?1")
//    Portfolio getPortfolioByID(Integer portfolioId);

}
