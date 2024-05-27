package unmsm.authservice.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unmsm.authservice.User.dto.response.UserDtoResponse;
import unmsm.authservice.User.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*@Query(value = "CALL my_procedure(:param1, :param2)", nativeQuery = true)
    void callMyProcedure(@Param("param1") String param1, @Param("param2") int param2);*/
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Query("update User u set u.firstname=:firstname, u.lastname=:lastname, u.country=:country " +
            "where u.id = :id")
    UserDtoResponse updateUser(@Param(value = "id") Integer id,
                               @Param(value = "firstname") String firstname,
                               @Param(value = "lastname") String lastname ,
                               @Param(value = "country") String country);
}
