package ru.pcs.graduatework.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StockDto {
    Integer id;
    String issuer;
    String ticker;
    Double quote;
}
