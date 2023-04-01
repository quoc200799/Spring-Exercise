## Bài 1. 
@Entity(name) dùng để chỉ tên của entity được Hibernate quản lý trong khi @Table(name) chỉ đích danh tên của table dưới database.

Mặc định tên table dưới database cũng sẽ giống với Entity class. Trong trường hợp tên table và tên entity không giống nhau, chúng ta có thể sử dụng @Table annotation để chỉ rõ table tương ứng với entity name.
```java
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Column(name = "name")  //Tên của cột tương ứng trên database
    private String name;
 
}
```
## Bài 2. 
2.1. Tiêu chuẩn đầu ra

Cách đơn giản nhất để kết xuất các truy vấn thành tiêu chuẩn là thêm vào application.properties :

>spring.jpa.show-sql=true

Để in ra đẹp hơn trong SQL, chúng ta có thể thêm:

>spring.jpa.properties.hibernate.format_sql=true

Mặc dù điều này cực kỳ đơn giản, nhưng nó không được khuyến khích , vì nó trực tiếp tải mọi thứ xuống tiêu chuẩn đầu ra mà không có bất kỳ tối ưu hóa nào của 1 framework…

2.2. Ghi lại Loggers

Bây giờ chúng ta hãy xem cách chúng ta có thể ghi lại các câu lệnh SQL bằng cách định cấu hình trình ghi nhật ký trong tệp thuộc tính:

>logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

Dòng đầu tiên ghi các truy vấn SQL và câu lệnh thứ hai ghi các tham số câu lệnh đã chuẩn bị.

## Bài 3.
Tham số id

## Bài 4.
* Trước khi lưu một entity mới – @PrePersist
* Trước khi cập nhật một entity – @PreUpdate
## Bài 5.
Kế thừa interface 
> interface NoRepositoryBean
## Bài 7.

* `@Column(unique = true)` là một phím tắt `UniqueConstraint` khi nó chỉ là một trường duy nhất.

* Đây là kiểu tự sinh dựa vào `IdentityGenerator` trong đó giá trị mong đợi sẽ được sinh ra bởi một cột định danh trong database, có nghĩa là chúng tự tăng.

Để sử dụng kiểu tự sinh này, chúng ta chỉ cần thiết lập đối số strategy:
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Id;
    // ...
}
```

khi ta dùng `IdentityGenerator` database sẽ hiểu là sẽ tự tăng so với dữ liệu mới nhất được sinh ra và sẽ k bị trùng lặp. Vì vậy khi dùng `@Column(unique = true)` là không cần thiết
## Bài 8.
```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select c " +
            "from employee " +
            "where (:lastname is null or c.lastname = :lastname) " +
            "and (:emailAddress is null or c.emailAddress = :emailAddress) " )
    List<Employee> findByLastNameAndEmailAddress(@Param("emailAddress") String emailAddress,
                                                    @Param("lastname") String lastName);
    
    @Query("select DISTINCT c " +
            "from post " +
            "where (:lastname is null or c.lastname = :lastname) " +
            "and (:emailAddress is null or c.emailAddress = :emailAddress) " )
    List<Employee> findByLastNameAndFirstNameDesc(@Param("firstname") String firstName,
                                                    @Param("lastname") String lastName);
    
    @Query("select u from Post u where u.lastname = ?1 order by firstName DESC")
    List<Employee> findByNameJPQL(@Param("lastname") String lastName);

    List<User> findByFirstNameContainingIgnoreCase(@Param("firstname") String firstName);
}
```
## Bài 9.

Có một cơ chế giúp chúng ta tạo ra các câu Query mà không cần viết thêm code. Cơ chế này xây dựng Query từ tên của method. Ví dụ:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByNameStartingWith(String prefix);
    List<Employee> findByNameContainingIgnoreCase(String name);
    /// ...
}
```
Với cách sử dụng annotation @Query, bạn sẽ có thể tự định nghĩa các method sử dụng câu truy vấn JPQL (Hibernate) hoặc raw SQL.
```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select u from Employee u where u.name = ?1")
    List<Employee> findByNameJPQL(String name);

    @Query("select u from Employee u where u.email = :email")
    Optional<Employee> findByEmailJPQL(@Param("email") String email);
    /// ...
}
```
## Bài 10.
sử dụng native Query
> @Query(
value = "select * from employee s where upper(s.name) like upper(concat('%', ?1, '%'))",
countQuery = "select count(s.id) from student s where upper(s.name) like upper(concat('%', ?1, '%'))",
nativeQuery = true
)
Page<Student> findByNameContainsIgnoreCase_NativeQuery(String name, Pageable pageable);

## Bài 11