package com.spring.ms.usuario.demo.mappers;

import com.spring.ms.usuario.demo.dto.UserDTO;
import com.spring.ms.usuario.demo.dto.jms.email.JmsEmailDetails;
import com.spring.ms.usuario.demo.model.UsuarioEntity;

public class JmsEmailMapper {

    public static JmsEmailDetails getUsuarioDTOToEmail(UserDTO userDTO , String tipoCorreo){
        return JmsEmailDetails
                .builder()
                .recipient(userDTO.getEmail())
                .subject(tipoCorreo)
                .build();
    }

    public static JmsEmailDetails getUsuarioEntityoEmail(UsuarioEntity user , String tipoCorreo){
        return JmsEmailDetails
                .builder()
                .recipient(user.getEmail())
                .subject(tipoCorreo)
                .build();
    }

}
