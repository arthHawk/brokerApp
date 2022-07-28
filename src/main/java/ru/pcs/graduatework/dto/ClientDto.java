package ru.pcs.graduatework.dto;

import lombok.Builder;
import lombok.Value;
import ru.pcs.graduatework.enums.Role;

import java.math.BigDecimal;

@Value
@Builder
public class ClientDto {
    Integer id;
    String login;
    String password;
    String surname;
    String name;
    BigDecimal cash;
    Role role;
}
