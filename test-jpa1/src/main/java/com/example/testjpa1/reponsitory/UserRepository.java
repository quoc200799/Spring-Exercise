package com.example.testjpa1.reponsitory;

import com.example.testjpa1.dto.UserDto;
import com.example.testjpa1.entity.User;
import com.example.testjpa1.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // 1. Query
//    List<UserDto> find
    // 2. JPQL
    @Query("select new com.example.testjpq1.dto.UserDto(u.id, u.name,u.email) from User u where u.name = ?1")
    List<UserDto> findUserDtoByName(String name);

    // 3. Native Query
    @Query(nativeQuery = true, name = "getUserDtoUsingNativeQuery")
    List<UserDto> getUserDtoUsingNativeQuery();

    // 4. Projection
    UserProjection findAllUserProjection();
}