package ru.pcs.graduatework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClientPortfolioDto {
    private Integer clientId;
    private String issuer;
    private String ticker;
    private Double quote;
    private Integer count;
}
