package com.spring.ms.usuario.demo.repository;

import com.spring.ms.usuario.demo.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
}
