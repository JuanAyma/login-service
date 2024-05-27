package unmsm.authservice.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unmsm.authservice.core.abstracts.IDataResult;
import unmsm.authservice.User.dto.response.UserDtoResponse;
import unmsm.authservice.User.dto.request.UserDtoRequest;
import unmsm.authservice.User.service.UserServiceImpl;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public IDataResult<UserDtoResponse> getUserById(@PathVariable Integer userId)
            throws ExecutionException, InterruptedException {
        return userService.getUserById(userId);
    }
    @PutMapping("/update")
    public IDataResult<UserDtoResponse> updateUser(@RequestBody UserDtoRequest userDtoRequest)
            throws ExecutionException, InterruptedException {
        return userService.updateUser(userDtoRequest);
    }

    @GetMapping("/listAll")
    public IDataResult<UserDtoResponse> getAllUsers()
            throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

}
