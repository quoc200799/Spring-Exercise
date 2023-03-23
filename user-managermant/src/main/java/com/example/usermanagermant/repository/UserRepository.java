package com.example.usermanagermant.repository;

import com.example.usermanagermant.db.UserDb;
import com.example.usermanagermant.model.User;
import com.example.usermanagermant.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, name = "getUserDtoUsingNativeQuery")
    List<UserDto> getUserDtoUsingNativeQuery();

    @Query("select new com.example.demojpa.dto.UserDto(u.id, u.name, u.email, u.phone, u.address, u.avatar) from User u where u.id = ?1")
    Optional<UserDto> findUserDtoById(Integer id);
}
