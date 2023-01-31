package com.ms.email.msemial.configs;


import lombok.Data;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "activemq")
public class JmsConfig {

    private static final String BROKER_URL = "broker-url";

    private static final String USER = "user";

    private static final String PASS = "pass";

    private Map<String, String> msemail = new LinkedHashMap<>();

    @Bean
    @Primary
    public ActiveMQConnectionFactory connectionFactoryLoyalty() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(msemail.get(BROKER_URL));
        connectionFactory.setUserName(msemail.get(USER));
        connectionFactory.setPassword(msemail.get(PASS));
        return connectionFactory;
    }

    @Bean
    @Primary
    public JmsTemplate jmsTemplateLoyalty() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactoryLoyalty());
        return template;
    }

    @Bean
    @Primary
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactoryLoyalty() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactoryLoyalty());
        factory.setPubSubDomain(false);
        return factory;
    }

}
