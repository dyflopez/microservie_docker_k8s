package com.spring.ms.usuario.demo.services.impl;

import com.spring.ms.usuario.demo.configs.MsEmailConfig;
import com.spring.ms.usuario.demo.dto.UserDTO;
import com.spring.ms.usuario.demo.mappers.JmsEmailMapper;
import com.spring.ms.usuario.demo.mappers.UsuarioMapper;
import com.spring.ms.usuario.demo.producer.IMsEmailProducer;
import com.spring.ms.usuario.demo.repository.IUsuarioRepository;
import com.spring.ms.usuario.demo.services.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private  final IUsuarioRepository iUsuarioRepository;

    private final IMsEmailProducer msEmailProducer;

    private final MsEmailConfig msEmailConfig;

    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository, IMsEmailProducer msEmailProducer, MsEmailConfig msEmailConfig) {
        this.iUsuarioRepository = iUsuarioRepository;
        this.msEmailProducer = msEmailProducer;
        this.msEmailConfig = msEmailConfig;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity getAllUser() {
        var listUser = iUsuarioRepository.findAll();
        return  ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @Override
    @Transactional
    public ResponseEntity deleterUser(long id) {
        ResponseEntity respose;
        var user = iUsuarioRepository.findById(id);
        if (user.isPresent()){
            iUsuarioRepository.deleteById(id);
            respose = ResponseEntity.status(HttpStatus.OK).build();
            msEmailProducer.GenerateTransactionEmail(
                    JmsEmailMapper.getUsuarioEntityoEmail(user.get(),
                            msEmailConfig.getTransactionType(MsEmailConfig.RETIRO)
                    )
            );

        }else{
            respose = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respose;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity getUserById(long id) {

        ResponseEntity respose;
        var user = iUsuarioRepository.findById(id);
        if (user.isPresent()){
            respose = ResponseEntity.status(HttpStatus.OK).body(user.get());
        }else{
            respose = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDTO());
        }
        return respose;
    }

    @Override
    public ResponseEntity updateUser(long id, UserDTO userDTO) {
        ResponseEntity respose;
        var user = iUsuarioRepository.findById(id);
        if (user.isPresent()){
            var updateUser = UsuarioMapper.getGenerateUserEntity(userDTO);
            updateUser.setId(id);
            iUsuarioRepository.save(updateUser);

            msEmailProducer.GenerateTransactionEmail(
                    JmsEmailMapper.getUsuarioDTOToEmail(userDTO,
                                                        msEmailConfig.getTransactionType(MsEmailConfig.ACTUALIZACION)
                    )
            );

            respose = ResponseEntity.status(HttpStatus.OK).build();
        }else{
            respose = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respose;
    }

    @Override
    @Transactional
    public ResponseEntity saveUser(UserDTO userDTO) {


        var user = UsuarioMapper.getGenerateUserEntity(userDTO);
        iUsuarioRepository.save(user);

        var jmsEmail =  JmsEmailMapper.getUsuarioDTOToEmail(userDTO,
                                                msEmailConfig.getTransactionType(MsEmailConfig.BIENVENIDO)
                                                );

        msEmailProducer.GenerateTransactionEmail(jmsEmail);

        return  ResponseEntity.status(HttpStatus.CREATED).body("Se creo el usuario");

    }


}
