package ru.pcs.graduatework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.graduatework.entities.ClientEntity;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByLogin(String login);
}
