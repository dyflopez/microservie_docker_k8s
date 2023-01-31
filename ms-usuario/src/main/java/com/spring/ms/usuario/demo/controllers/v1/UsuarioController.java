package com.spring.ms.usuario.demo.controllers.v1;

import com.spring.ms.usuario.demo.controllers.v1.docs.UsuarioDocs;
import com.spring.ms.usuario.demo.dto.UserDTO;
import com.spring.ms.usuario.demo.services.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioDocs {

    private final IUsuarioService iUsuarioService;

    @Override
    @PostMapping
    public ResponseEntity createUSer(UserDTO userDTO) {
        return iUsuarioService.saveUser(userDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity getAllUSer() {
        return iUsuarioService.getAllUser();
    }

    @Override
    @GetMapping("/byid")
    public ResponseEntity getUSerById(long id) {
        return iUsuarioService.getUserById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUSerById(long id) {
        return iUsuarioService.deleterUser(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity updateUser(long id, UserDTO userDTO) {
        return iUsuarioService.updateUser(id,userDTO);
    }


}
