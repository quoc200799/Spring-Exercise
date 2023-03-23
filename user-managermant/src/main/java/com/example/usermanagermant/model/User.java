package com.example.usermanagermant.model;

import com.example.usermanagermant.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

//Bước 2
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "userInfo",
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "phone", type = String.class),
                                @ColumnResult(name = "address", type = String.class),
                                @ColumnResult(name = "avatar", type = String.class)
                        }
                )
        )
})
//Bước 1
@NamedNativeQuery(
        name = "getUserDtoUsingNativeQuery", // ĐẶt tên cho câu lệnh
        resultSetMapping = "userInfo", // Đặt tên cho kết quả sử dụng ở bước trên
        query = "select u.id, u.name, u.email, u.phone, u.address, u.avatar from user u") // Định nghĩa querry
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user-table")
@Builder
@ToString
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "password")
    private String password;
}
