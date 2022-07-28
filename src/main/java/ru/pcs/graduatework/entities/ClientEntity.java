package ru.pcs.graduatework.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import ru.pcs.graduatework.enums.Role;
import ru.pcs.graduatework.enums.State;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    private String surname;
    private String name;
    @Column(columnDefinition = "default 0")
    private BigDecimal cash;

    // если сделать EnumType.ORDINAL, то будет храниться порядковый номер числа
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "clientEntity")
    private List<PortfolioEntity> portfolioEntities;


}
