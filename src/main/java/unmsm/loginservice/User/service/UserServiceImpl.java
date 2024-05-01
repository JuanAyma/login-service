package unmsm.loginservice.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unmsm.loginservice.User.core.abstracts.IDataResult;
import unmsm.loginservice.User.core.results.ErrorDataResult;
import unmsm.loginservice.User.core.results.SuccessDataResult;
import unmsm.loginservice.User.dto.response.UserDtoResponse;
import unmsm.loginservice.User.dto.resquest.UserDtoRequest;
import unmsm.loginservice.User.entity.User;
import unmsm.loginservice.User.entity.UserRole;
import unmsm.loginservice.User.repository.UserRepository;
import unmsm.loginservice.User.common.HttpStatusCodes;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public IDataResult<UserDtoResponse> getUserById(Integer userId) {

        UserDtoResponse userDtoResponse = null;
        try {
            User user = userRepository.findById(userId).
                    orElseThrow(() -> new RuntimeException("No se encontró el usuario con id: " + userId));
            UserDtoResponse.builder()
                    .id(Math.toIntExact(user.getId()))
                    .username(user.getUsername())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .country(user.getCountry())
                    .email(user.getEmail())
                    .build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return new SuccessDataResult(userDtoResponse);
    }

    @Override
    @Transactional
    public IDataResult<UserDtoResponse> updateUser(UserDtoRequest userDtoRequest) {
        try {
            if (userDtoRequest.getId().equals(0))
                return new ErrorDataResult(null, HttpStatusCodes.HTTP_INTERNAL_SERVER_ERROR, "ProjectId must to be not null");

            User user = User.builder()
                    .id(userDtoRequest.getId())
                    .username(userDtoRequest.getUsername())
                    .firstname(userDtoRequest.getFirstname())
                    .lastname(userDtoRequest.getLastname())
                    .country(userDtoRequest.getCountry())
                    .role(UserRole.USER)
                    .build();
            userRepository.updateUser(user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getCountry());

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return new ErrorDataResult(null,HttpStatusCodes.HTTP_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new SuccessDataResult(userDtoRequest);
    }

    @Override
    public IDataResult<UserDtoResponse> getAllUsers() {
        List<UserDtoResponse> listUserDtoResponse = new ArrayList<>();
        try {
            List<User> listUser = userRepository.findAll();

            listUser.forEach(user -> {
               UserDtoResponse userDtoResponse = UserDtoResponse.builder()
                       .id(Math.toIntExact(user.getId()))
                       .username(user.getUsername())
                       .firstname(user.getFirstname())
                       .lastname(user.getLastname())
                       .country(user.getCountry())
                       .email(user.getEmail())
                       .build();
                listUserDtoResponse.add(userDtoResponse);
            });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return new SuccessDataResult(listUserDtoResponse);
    }

}
