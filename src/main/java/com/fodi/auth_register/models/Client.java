package com.fodi.auth_register.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "client")
public class Client {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
//    @NotEmpty(message = "Имя не может быть пустым")
    private String username;
    @Column(name = "email_client")
    @Email(message = "Введите корректный email")
    private String email;
//    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 7, message = "Пароль должен быть больше 7 символов")
    private String password;
//    @NotEmpty(message = "Номер не может быть пустым")
    private String phoneNumber;
    private String googleName;
    private String googleUsername;
}
