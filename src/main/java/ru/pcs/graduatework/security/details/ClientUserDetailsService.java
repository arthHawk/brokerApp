package ru.pcs.graduatework.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.repositories.ClientsRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientUserDetailsService implements UserDetailsService {
    private final ClientsRepository clientsRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

//        /* Вместо этих четырех строк будем записывать все в одну строку
        Optional<Client> clientOptional = clientsRepository.findByLogin(login);
        Client client = clientOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        ClientUserDetails clientUserDetails = new ClientUserDetails(client);
        return clientUserDetails;
//        */

//        return new ClientUserDetails(clientsRepository.findByLogin(login)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

}
