package com.spring.ms.usuario.demo.controllers.v1.docs;

import com.spring.ms.usuario.demo.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Servicio para transacciones ")
public interface UsuarioDocs {

    @Operation(summary = "Servicio para registrar el usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "usuario creado",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    ResponseEntity createUSer(
            @RequestBody UserDTO userDTO
            );

    @Operation(summary = "listar todos los usuarios")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "lista de usuarios",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    ResponseEntity getAllUSer();

    @Operation(summary = "buscar por id de usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "retorna un usuario",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    ResponseEntity getUSerById(@RequestParam long id);

    @Operation(summary = "eliminar usuario por ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    ResponseEntity deleteUSerById(@PathVariable long id);

    @Operation(summary = "actualizar informacion del  usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE
                                    )
                            }
                    )
            }
    )
    ResponseEntity updateUser(@PathVariable long id , @RequestBody UserDTO userDTO);
}
