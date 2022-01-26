package ru.pcs.graduatework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    private String surname;
    private String name;
    @Column(columnDefinition = "default 0")
    private BigDecimal cash;

    // роль пользователя
    public enum Role {
        USER, ADMIN
    }

    // состояние пользователя - подтвержден, не подтвержден
    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    // если сделать EnumType.ORDINAL, то будет хранится порядковый номер числа
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "client")
    private List<Portfolio> portfolios;


}
