package ru.pcs.graduatework.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.DtoFactory;
import ru.pcs.graduatework.entities.ClientEntity;
import ru.pcs.graduatework.entities.PortfolioEntity;
import ru.pcs.graduatework.enums.Role;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.repositories.ClientsRepository;
import ru.pcs.graduatework.repositories.PortfoliosRepository;
import ru.pcs.graduatework.service.ClientsService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;
    private final PortfoliosRepository portfoliosRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addClient(ClientForm form) {
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setLogin(form.getLogin());
        clientEntity.setPassword(passwordEncoder.encode(form.getPassword()));
        clientEntity.setSurname(form.getSurname());
        clientEntity.setName(form.getName());
        clientEntity.setRole(Role.USER);
        clientEntity.setCash(BigDecimal.ZERO);

        clientsRepository.save(clientEntity);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientsRepository.deleteById(clientId);
    }


    @Override
    public List<ClientDto> getAllClients() {
        List<ClientEntity> clientEntities = clientsRepository.findAll();
        List<ClientDto> clientDtoList = clientEntities.stream()
                .map(clientEntity ->
                        DtoFactory.createClientDtoForAdmin(clientEntity)
                )
                .collect(Collectors.toList());

        return clientDtoList;
    }

    @Override
    public ClientDto searchClientByLogin(String login) {
        List<ClientEntity> clientsList = clientsRepository.findAll();
        ClientEntity clientEntity;
        try {
            clientEntity = clientsList.stream()
                    .filter(cl -> cl.getLogin().equals(login))
                    .findFirst()
                    .get();
        } catch (RuntimeException e) {
            return null;
        }
        return DtoFactory.createClientDtoAuth(clientEntity);
    }

    @Override
    public Boolean authorization(ClientEntity clientEntity, ClientForm form) {
        return clientEntity.getLogin().equals(form.getLogin()) &&
                clientEntity.getPassword().equals(form.getPassword());
    }

    @Override
    public void addCash(Integer clientId, BigDecimal addingCashValue) {
        ClientEntity clientEntity = clientsRepository.getById(clientId);
        clientEntity.setCash(clientEntity.getCash().add(addingCashValue));
        clientsRepository.save(clientEntity);
    }

    @Override
    public void withdrawalCash(Integer clientId, BigDecimal withdrawalCashValue) {
        ClientEntity clientEntity = clientsRepository.getById(clientId);
        if (clientEntity.getCash().compareTo(withdrawalCashValue) <= 0) {
            clientEntity.setCash(BigDecimal.ZERO);
        } else {
            clientEntity.setCash(clientEntity.getCash().subtract(withdrawalCashValue));
        }
        clientsRepository.save(clientEntity);

    }

    @Override
    public void editClient(Integer clientId, ClientForm form) {
        ClientEntity clientEntity = clientsRepository.getById(clientId);
        if (form.getLogin() != null && form.getLogin().length() != 0) {
            clientEntity.setLogin(form.getLogin());
        }
        if (form.getPassword() != null && form.getPassword().length() != 0) {
            clientEntity.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        if (form.getName() != null && form.getName().length() != 0) {
            clientEntity.setName(form.getName());
            ;
        }
        if (form.getSurname() != null && form.getSurname().length() != 0) {
            clientEntity.setSurname(form.getSurname());
            ;
        }

        clientsRepository.save(clientEntity);
    }

    @Override
    public boolean isLoginUnique(String login) {
        ClientDto clientDto = searchClientByLogin(login);
        if (clientDto == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ClientDto getClient(Integer clientId) {
        ClientEntity clientEntity = clientsRepository.getById(clientId);

        return DtoFactory.createClientDtoAuth(clientEntity);
    }

    @Override
    public List<PortfolioEntity> getPortfoliosByUser(Integer clientId) {
        return portfoliosRepository.findAllByClientEntity_Id(clientId);
    }

    @Override
    public List<PortfolioEntity> getPortfoliosWithoutOwner() {
        return null;
    }
}
