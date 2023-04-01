package com.example.testjpa1.entity;

import com.example.testjpa1.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "useInfo",
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type = String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDtoUsingNativeQuery",
        resultSetMapping = "useInfo",
        query = "select u.id, u.name, u.email from user u")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private String password;

}