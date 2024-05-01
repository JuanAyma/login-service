package unmsm.loginservice.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import unmsm.loginservice.User.core.abstracts.IDataResult;
import unmsm.loginservice.User.dto.response.UserDtoResponse;
import unmsm.loginservice.User.dto.resquest.UserDtoRequest;
import unmsm.loginservice.User.service.UserServiceImpl;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
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
