package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.graduatework.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByLogin(String login);
}
