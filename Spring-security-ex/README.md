### 1.	Trình bày khái niệm liên quan đến các đối tượng:
- `UserNamePasswordAuthenticationToken` là một class có chứa 2 object là `principal`(username) và `credentials`(password). Trong class, có 2 contructors được sử dụng cho 2 trường hợp khác nhau: `login`(gồm username, password) và `phân quyền`(gồm username, password, roles)
- `AuhenticationManager` là một interface chỉ chứa 1 phương thức duy nhất là authenticate. Dùng để valid sau khi xác thực, khi thành công sẽ trả về Authentication nếu thất bại trả về một exception là AuthenticationException
- `AuhenticationProvider` nó sử dụng `PasswordEncoder` và `UserDetailsService` để tìm kiếm xem username đấy có không vaf cái password của nó có trùng nhau không? Nếu có thì sẽ trả về AuhenticationManager
- `PasswordEncoder` là interface có nhiệm vụ encode, encrypt và decrypt password của user, validate và trả về kết quả valid/invalid cho Authentication Provider xử lý.
- `UserDetailsService` là core-interface của spring có chức năng tổng hợp thông tin và đóng gói lại thành một object UserDetails. Nó có một method duy nhất là loadUserByUsername(String userName) dùng để tìm kiếm thông tin user dựa trên username. Có thể override lại hàm này để viết lại tìm kiếm user riêng của hệ thống mình
- `UserDetails` là interface đại diện cho principal, cung cấp các method:
  + getAuthorities(): trả về danh sách các quyền của principal
  + getPassword(): trả về password đã dùng trong quá trình xác thực
  + getUsername(): trả về username đã dùng trong quá trình xác thực
  + isEnabled(): trả về true nếu principal đã được xác thực
  + isAccountNonExpired(): trả về true nếu tài khoản người dùng chưa hết hạn
  + isAccountNonLocked(): trả về true nếu tài khoản người dùng chưa bị khóa
  + isCredentialsNonExpired(): trả về true nếu chứng thực (mật khẩu) của principal chưa hết hạn

### 2. Trình bày sơ lược về wordflow của spring security
Workflow của Spring Boot hoạt động như sau:
- Mỗi request vào hệ thống sẽ được trải qua một tập các Filter, tập các filter này được quản lý bởi một `springSecurityFilterChain`, cụ thể ở đây là `DelegatingFilterProxy`. Bản chất `fillterProxy` này giữ một tập các filter đã được định nghĩa trước và lần lượt cho request đi qua filter một.
- Mỗi một filter được xử lý bởi một `AuthenticationProvider`.
- `AuthenticationProvider` sử dụng `UserDetailsService` lấy ra các thông tin liên quan đến User (thông tin cơ bản, phân quyền). Thông tin được gói trong cài đặt cụ thể của interface `UserDetails`.
- Với dữ liệu có được từ `userDetailService`, `AuthenticationProvider` tiến hành xác thực với dữ liệu đầu vào từ request. Xác thực không thành công trả về lỗi 401. Nếu thành công, một object Authentication chứa object `UserDetails` sẽ được lưu vào trong `SecurityContextHolder`. Việc xác thực kết thúc đến kiểm tra phân quyền. Spring security sẽ kiểm tra object Authentication có quyền thực hiện request không bằng cách check Authorities - mảng các quyền của object đó. Không đủ quyền thì báo lỗi 403.
### 4. Session, cookie 
 >`Session` đơn giản là 1 cách để chúng ta lưu lại dữ liệu của người dùng sử dụng website. Giá trị của session được lưu trong một tập tin trên máy chủ.

> `Cookie` là một đoạn văn bản ghi thông tin được tạo ra và lưu trên trình duyệt của máy người dùng. Cookie thường được tạo ra khi người dùng truy cập một website, cookie sẽ ghi nhớ những thông tin như tên đăng nhập, mật khẩu, các tuỳ chọn do người dùng lựa chọn đi kèm.

| Session                                                                            | Cookie                                                                      |
|------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Cookie được lưu trữ trên trình duyệt của người dùng.                               | Session không được lưu trữ trên trình duyệt.                                |
| Dữ liệu cookie được lưu trữ ở phía client.                                         | Dữ liệu session được lưu trữ ở phía server.                                 |
| Dữ liệu cookie dễ dàng sửa đổi hoặc đánh cắp khi chúng được lưu trữ ở phía client. | Dữ liệu session không dễ dàng sửa đổi vì chúng được lưu trữ ở phía máy chủ. | 
| Dữ liệu cookie có sẵn trong trình duyệt đến khi expired.                           | Sau khi đóng trình duyệt sẽ hết phiên làm việc (session)                    |


