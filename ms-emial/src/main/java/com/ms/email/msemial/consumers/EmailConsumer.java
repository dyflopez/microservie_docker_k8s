package com.ms.email.msemial.consumers;

import com.ms.email.msemial.configs.MsEmailConfig;
import com.ms.email.msemial.dto.jms.JmsEmailDetails;
import com.ms.email.msemial.service.IEmailService;
import com.ms.email.msemial.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;


@Slf4j
@Component
@ConditionalOnProperty(value = "activemq.jms-listener.enabled", matchIfMissing = true)
public class EmailConsumer {


    private  final IEmailService iEmailService;

    private  final MsEmailConfig msEmailConfig;


    public EmailConsumer(IEmailService iEmailService, MsEmailConfig msEmailConfig) {
        this.iEmailService = iEmailService;
        this.msEmailConfig = msEmailConfig;
    }


    @JmsListener(destination = "${activemq.msemail.transaction-status-changes.queue}",
                 containerFactory = "jmsListenerContainerFactoryLoyalty")
    public void transactionStatusChange(Message<String> message, Session session) throws JMSException {

        JmsEmailDetails details = new JmsEmailDetails();


        try {

            String jsonMessage = message.getPayload();
            details = JsonUtils.jsonToObject(jsonMessage, JmsEmailDetails.class);
            details.setMsgBody(mensajeEmail(details.getSubject()));

            log.trace("ActiveMQ incomming message from {}: \n{}", jsonMessage);

            iEmailService.sendSimpleMail(details);

        } catch (Exception e) {
            log.error("Error proccessing JSON message\n{}", e.getLocalizedMessage());
        }
    }

    private String mensajeEmail(String tipoMensaje){

        String body;

        if(tipoMensaje.equalsIgnoreCase(MsEmailConfig.BIENVENIDO)){
            body=msEmailConfig.getBodyMensaje(MsEmailConfig.BIENVENIDO);
        }else if(tipoMensaje.equalsIgnoreCase(MsEmailConfig.ACTUALIZACION)){
            body=msEmailConfig.getBodyMensaje(MsEmailConfig.ACTUALIZACION);
        }else if(tipoMensaje.equalsIgnoreCase(MsEmailConfig.RETIRO)){
            body=msEmailConfig.getBodyMensaje(MsEmailConfig.RETIRO);
        }else{
            body="Gracias por estar en nuestra plataformav";
        }

        return body;
    }

}
