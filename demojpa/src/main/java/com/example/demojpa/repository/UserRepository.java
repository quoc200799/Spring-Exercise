package com.example.demojpa.repository;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //1. Method query (creation query): Đặt tên method theo đúng chuẩn được quy định

    List<User> findByName(String name);

    List<User> findByNameStartingWith(String prefix);

    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByEmail(String email);

    boolean existsByName(String email);

    long countByName(String name);

    List<User> findByNameOrderByEmailDesc(String name);

    //2. JPQL(JPA Query Language): Query dựa theo entity
    @Query("select u from User u where u.name = ?1")
    List<User> findByNameJPQL(String name);

    @Query("select u from User u where u.email = :email")
    List<User> findByEmailJPQL(@Param("email") String email);

    //3. Native Query: Viết câu lệnh native dựa theo database
    @Query(nativeQuery = true, value = "select u from User u where u.name = ?1")
    List<User> findByName_NativeQuery(String name);

    @Query(nativeQuery = true, value = "select u from User u where u.name = ?1 limit 5")
    List<User> findTop5ByName_NativeQuery(String name);

    // Query DTO
    // 1. Query Entity => mapper DTO
    // 2. JPQL
    // 3. Native Query
    // 4. Projection(interface)
    @Query("select new com.example.demojpa.dto.UserDto(u.id, u.name, u.email) from User u where u.name = ?1")
    List<UserDto> findUserDto(String name);
}
