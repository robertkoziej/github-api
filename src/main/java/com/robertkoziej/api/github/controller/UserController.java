package com.robertkoziej.api.github.controller;

import com.robertkoziej.api.github.model.UserResponse;
import com.robertkoziej.api.github.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.robertkoziej.api.github.controller.ServiceMappings.GET__USERS__LOGIN;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(GET__USERS__LOGIN)
    public UserResponse getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }
}
