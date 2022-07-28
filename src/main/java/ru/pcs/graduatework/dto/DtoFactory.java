package ru.pcs.graduatework.dto;

import ru.pcs.graduatework.entities.ClientEntity;
import ru.pcs.graduatework.entities.StockEntity;

public class DtoFactory {
    public static ClientDto createClientDtoAuth(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .surname(clientEntity.getSurname())
                .login(clientEntity.getLogin())
                .cash(clientEntity.getCash())
                .build();
    }

    public static ClientDto createClientDtoForAdmin(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .surname(clientEntity.getSurname())
                .login(clientEntity.getLogin())
                .build();
    }

    public static StockDto createStockDto(StockEntity stockEntity) {
        return StockDto.builder()
                .id(stockEntity.getId())
                .issuer(stockEntity.getIssuer())
                .quote(stockEntity.getQuote())
                .ticker(stockEntity.getTicker())
                .build();
    }
}
