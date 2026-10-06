package br.com.wbytesistemas.tasker_api.controller;

import br.com.wbytesistemas.tasker_api.dto.UserRequestDTO;
import br.com.wbytesistemas.tasker_api.dto.UserResponseDTO;
import br.com.wbytesistemas.tasker_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public UserResponseDTO create(
                @Valid @RequestBody UserRequestDTO dto) {

            return userService.create(dto);
        }

        @GetMapping
        public List<UserResponseDTO> findAll() {
            return userService.findAll();
        }
    }
