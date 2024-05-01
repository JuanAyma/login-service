package unmsm.loginservice.User.service;
import unmsm.loginservice.User.core.abstracts.IDataResult;
import unmsm.loginservice.User.dto.response.UserDtoResponse;
import unmsm.loginservice.User.dto.resquest.UserDtoRequest;

public interface IUserService {

    IDataResult<UserDtoResponse> getUserById(Integer userId);
    IDataResult<UserDtoResponse> updateUser(UserDtoRequest userDtoRequest);
    IDataResult<UserDtoResponse> getAllUsers();
}
