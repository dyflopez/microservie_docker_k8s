package com.spring.ms.usuario.demo.producer.impl;

import com.spring.ms.usuario.demo.dto.jms.email.JmsEmailDetails;
import com.spring.ms.usuario.demo.producer.IMsEmailProducer;
import com.spring.ms.usuario.demo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsEmailProducerImpl implements IMsEmailProducer {


    @Value("${activemq.msemail.generate-transactions.queue}")
    private String generateTransactionsTopic;

    private final JmsTemplate jmsTemplate;

    public MsEmailProducerImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendGenerateTransaction(String message) {
        //final String messageBody = JsonUtils.convertToJson(message);
        try {
            log.trace(
                    "ActiveMQ outgoing message to queue {}: \n{}",
                    generateTransactionsTopic,
                    message
            );
            jmsTemplate.setPubSubDomain(false);

            jmsTemplate.convertAndSend(
                    generateTransactionsTopic,
                    message
            );
            log.trace(
                    "ActiveMQ outgoing message sent succesfully to topic {}",
                    generateTransactionsTopic
            );
        } catch (JmsException e) {
            log.error(
                    String.format(
                            "Error sending message to topic %s",
                            generateTransactionsTopic
                    ),
                    e
            );
        }
    }

    @Override
    public void GenerateTransactionEmail(JmsEmailDetails jmsEmailDetails) {
        try {

            final String messageBody = JsonUtils.convertToJson(jmsEmailDetails);

            log.trace(
                    "ActiveMQ outgoing message to queue {}: \n{}",
                    generateTransactionsTopic,
                    messageBody
            );
            jmsTemplate.setPubSubDomain(false);

            jmsTemplate.convertAndSend(
                    generateTransactionsTopic,
                    messageBody
            );
            log.trace(
                    "ActiveMQ outgoing message sent succesfully to topic {}",
                    generateTransactionsTopic
            );
        } catch (JmsException e) {
            log.error(
                    String.format(
                            "Error sending message to topic %s",
                            generateTransactionsTopic
                    ),
                    e
            );
        }
    }


}
