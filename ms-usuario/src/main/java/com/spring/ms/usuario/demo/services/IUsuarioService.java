package com.spring.ms.usuario.demo.services;

import com.spring.ms.usuario.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {

    ResponseEntity getAllUser();

    ResponseEntity deleterUser(long id);

    ResponseEntity getUserById(long id);

    ResponseEntity updateUser(long id , UserDTO userDTO);

    ResponseEntity saveUser(UserDTO userDTO);

}
