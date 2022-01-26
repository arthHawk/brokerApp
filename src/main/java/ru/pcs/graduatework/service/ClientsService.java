package ru.pcs.graduatework.service;

import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;

import java.math.BigDecimal;
import java.util.List;

public interface ClientsService {
    List<Client> getAllClients();
    Client searchClientByLogin(String login);
    Boolean authorization(Client client, ClientForm form);
    void addClient(ClientForm form);
    void deleteClient(Integer clientId);
    Client getClient(Integer clientId);

    List<Portfolio> getPortfoliosByUser(Integer clientId);
    List<Portfolio> getPortfoliosWithoutOwner();

    void addCash(Integer clientId, BigDecimal addingValue);
    void withdrawalCash(Integer clientId, BigDecimal withdrawalValue);

    void editClient(Integer clientId, ClientForm form);

    boolean isLoginUnique(String login);
}
