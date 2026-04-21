package cl.duoc.demologin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import cl.duoc.demologin.dto.ApiResponse;
import cl.duoc.demologin.dto.UserDto;
import cl.duoc.demologin.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetExchange("/list")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(){
        List<UserDto> users= userService.getAllUserDTO();
        ApiResponse<List<UserDto>> response =
            new ApiResponse<List<UserDto>>(200, "Listado de Usuarios", users);
        return ResponseEntity.ok(response);
    }
}
