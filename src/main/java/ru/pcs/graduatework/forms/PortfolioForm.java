package ru.pcs.graduatework.forms;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PortfolioForm {
    private Integer clientId;
    private String issuer;
    private String ticker;
    private Double quote;
    private Integer count;
}
