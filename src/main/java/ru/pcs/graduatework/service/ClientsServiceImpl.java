package ru.pcs.graduatework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.repositories.ClientsRepository;
import ru.pcs.graduatework.repositories.PortfoliosRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;
    private final PortfoliosRepository portfoliosRepository;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public ClientsServiceImpl(ClientsRepository clientsRepository) {
//        this.clientsRepository = clientsRepository;
//    }

    @Override
    public void addClient(ClientForm form) {
        Client client = Client.builder()
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .surname(form.getSurname())
                .name(form.getName())
                .role(Client.Role.USER)
                .cash(BigDecimal.ZERO)
                .build();
        clientsRepository.save(client);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientsRepository.deleteById(clientId);
    }


    @Override
    public List<Client> getAllClients() {
        return clientsRepository.findAll();
    }

    @Override
    public Client searchClientByLogin(String login) {
        List<Client> clientsList = clientsRepository.findAll();
        Client client;
        try {
            client = clientsList.stream()
                    .filter(cl -> cl.getLogin().equals(login))
                    .findFirst()
                    .get();
        } catch (RuntimeException e) {
            return null;
        }
            return client;
    }

    @Override
    public Boolean authorization(Client client, ClientForm form) {
        return client.getLogin().equals(form.getLogin()) &&
                client.getPassword().equals(form.getPassword());
    }

    @Override
    public void addCash(Integer clientId, BigDecimal addingCashValue) {
        Client client = clientsRepository.getById(clientId);
        client.setCash(client.getCash().add(addingCashValue));
        clientsRepository.save(client);
    }

    @Override
    public void withdrawalCash(Integer clientId, BigDecimal withdrawalCashValue) {
        Client client = clientsRepository.getById(clientId);
        if (client.getCash().compareTo(withdrawalCashValue) <= 0) {
            client.setCash(BigDecimal.ZERO);
        } else {
            client.setCash(client.getCash().subtract(withdrawalCashValue));
        }
        clientsRepository.save(client);

    }

    @Override
    public void editClient(Integer clientId, ClientForm form) {
        Client client = clientsRepository.getById(clientId);
        if (form.getLogin() != null && form.getLogin().length() != 0) {client.setLogin(form.getLogin());}
        if (form.getPassword() != null && form.getPassword().length() != 0) {
            client.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        if (form.getName() != null && form.getName().length() != 0) {client.setName(form.getName());;}
        if (form.getSurname() != null && form.getSurname().length() != 0) {client.setSurname(form.getSurname());;}

        clientsRepository.save(client);
    }

    @Override
    public boolean isLoginUnique(String login) {
        Client client = searchClientByLogin(login);
        if (client == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Client getClient(Integer clientId) {
        return clientsRepository.getById(clientId);
    }

    @Override
    public List<Portfolio> getPortfoliosByUser(Integer clientId) {
        return portfoliosRepository.findAllByClient_Id(clientId);
    }

    @Override
    public List<Portfolio> getPortfoliosWithoutOwner() {
        return null;
    }
}
