package com.spring.ms.usuario.demo.dto.jms.email;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JmsEmailDetails {

    private String recipient;

    private String msgBody;

    private String subject;

    private String attachment;

}
