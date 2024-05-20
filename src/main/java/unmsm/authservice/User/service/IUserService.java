package unmsm.authservice.User.service;
import unmsm.authservice.core.abstracts.IDataResult;
import unmsm.authservice.User.dto.response.UserDtoResponse;
import unmsm.authservice.User.dto.request.UserDtoRequest;

public interface IUserService {

    IDataResult<UserDtoResponse> getUserById(Integer userId);
    IDataResult<UserDtoResponse> updateUser(UserDtoRequest userDtoRequest);
    IDataResult<UserDtoResponse> getAllUsers();
}
