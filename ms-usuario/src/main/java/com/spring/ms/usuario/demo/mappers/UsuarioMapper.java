package com.spring.ms.usuario.demo.mappers;

import com.spring.ms.usuario.demo.dto.UserDTO;
import com.spring.ms.usuario.demo.model.UsuarioEntity;
import org.apache.catalina.User;

public class UsuarioMapper {

    private   UsuarioMapper(){

    }



    public static UsuarioEntity getGenerateUserEntity(UserDTO userDTO){
        return  UsuarioEntity
                .builder()
                .email(userDTO.getEmail())
                .nombre(userDTO.getNombre())
                .password(userDTO.getPassword())
                .build();
    }



}
