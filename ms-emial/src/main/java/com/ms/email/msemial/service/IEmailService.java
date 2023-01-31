package com.ms.email.msemial.service;

import com.ms.email.msemial.dto.jms.JmsEmailDetails;

public interface IEmailService {

    void sendSimpleMail(JmsEmailDetails details);

}
