package com.upc.petminder.controllers;

import com.upc.petminder.dtos.UserDTO.UserDto;
import com.upc.petminder.dtos.UserDTO.UserIdDto;
import com.upc.petminder.entities.Users;
import com.upc.petminder.serviceinterfaces.UserService;
import io.jsonwebtoken.Jwt;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Listar todos los Users existentes
    @GetMapping("/api/user/findall-users")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    //Registrar usuario
    @PostMapping("api/libre/registrar-user")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    // Modificar un usuario
    @PutMapping("/api/user/actualizar-user/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        // Llamar al service para actualizar el usuario
        UserDto updatedUserDto = userService.update(id, userDto);

        return ResponseEntity.ok(updatedUserDto);
    }
    //Eliminar user
    @DeleteMapping("/api/user/eliminar-user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();  // Devolver un status 204 No Content
    }

    // Endpoint para obtener el user_id por username
    @GetMapping("/api/user/id-by-username/{username}")
    public ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserIdByUsername(username);
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userId);
    }

    //Id segun el username
    @GetMapping("/api/user/{username}")
    public ResponseEntity<UserIdDto> UserIdByUsername(@PathVariable String username) {
        Long userId = userService.getUserIdByUsername(username);
        // Verifica si el usuario fue encontrado
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Retorna el ID envuelto en el DTO
        UserIdDto userIdDto = new UserIdDto(userId);
        return ResponseEntity.ok(userIdDto);
    }

}
