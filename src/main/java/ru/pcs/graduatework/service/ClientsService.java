package ru.pcs.graduatework.service;

import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.entities.ClientEntity;
import ru.pcs.graduatework.entities.PortfolioEntity;
import ru.pcs.graduatework.forms.ClientForm;

import java.math.BigDecimal;
import java.util.List;

public interface ClientsService {
    List<ClientDto> getAllClients();

    ClientDto searchClientByLogin(String login);

    Boolean authorization(ClientEntity clientEntity, ClientForm form);

    void addClient(ClientForm form);

    void deleteClient(Integer clientId);

    ClientDto getClient(Integer clientId);

    List<PortfolioEntity> getPortfoliosByUser(Integer clientId);

    List<PortfolioEntity> getPortfoliosWithoutOwner();

    void addCash(Integer clientId, BigDecimal addingValue);

    void withdrawalCash(Integer clientId, BigDecimal withdrawalValue);

    void editClient(Integer clientId, ClientForm form);

    boolean isLoginUnique(String login);
}
