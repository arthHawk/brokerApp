package ru.pcs.graduatework.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String issuer;
    private String ticker;
    private Double quote;

    @OneToMany(mappedBy = "stock")
    private List<Portfolio> portfolios;
}
