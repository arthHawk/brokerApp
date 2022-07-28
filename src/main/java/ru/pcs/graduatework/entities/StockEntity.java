package ru.pcs.graduatework.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String issuer;
    private String ticker;
    private Double quote;

    @OneToMany(mappedBy = "stockEntity")
    private List<PortfolioEntity> portfolioEntities;
}
