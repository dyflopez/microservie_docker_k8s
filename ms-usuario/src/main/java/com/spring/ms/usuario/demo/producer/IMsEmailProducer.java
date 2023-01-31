package com.spring.ms.usuario.demo.producer;

import com.spring.ms.usuario.demo.dto.jms.email.JmsEmailDetails;

public interface IMsEmailProducer {

    void sendGenerateTransaction(
           // JmsLoyaltyGenerateTransactionDTO message
            String message
    );

    void GenerateTransactionEmail(JmsEmailDetails jmsEmailDetails);
}
