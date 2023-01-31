package com.spring.ms.usuario.demo.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String nombre;

    private String email;

    private String password;

}
