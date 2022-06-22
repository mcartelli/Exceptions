package com.santander.exceptions.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NotNull(message = "El campo dni no puede estar vacío")
    private Long dni;
    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El campo apellido no puede estar vacío")
    private String lastName;
    @NotBlank(message = "El campo email no puede estar vacío")
    @Email(message = "Debe ser un email válido")
    private String email;
    @NotBlank(message = "El campo contraseña no puede estar vacío")
    private String password;

}
