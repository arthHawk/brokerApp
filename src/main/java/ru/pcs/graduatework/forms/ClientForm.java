package ru.pcs.graduatework.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ClientForm {
    private Integer id;

    @NotEmpty
    @Size(min = 4, max = 20)
    private String login;

    @Size(min = 5, max = 60)
    @NotEmpty
    private String password;

    @Size(min = 2, max = 50)
    @NotEmpty
    private String surname;

    @Size(min = 2, max = 50)
    @NotEmpty
    private String name;
}
