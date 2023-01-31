package com.spring.ms.usuario.demo.controllers.echo;

import com.spring.ms.usuario.demo.controllers.echo.docs.EchoDocs;
import com.spring.ms.usuario.demo.producer.IMsEmailProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/echo")
public class EchoController implements EchoDocs {

    final IMsEmailProducer msEmailProducer;

    public EchoController(IMsEmailProducer msEmailProducer) {
        this.msEmailProducer = msEmailProducer;
    }

    @Override
    @GetMapping
    public ResponseEntity echo(String message) {
        msEmailProducer.sendGenerateTransaction(message);
        return ResponseEntity.ok(message);
    }
}
