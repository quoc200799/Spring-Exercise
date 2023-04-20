/**
 * action: là object chứa thông tin về sự kiện{
 * type: loại hàng động, || URL API "GET /api/v1/users" (bắt buộc,duy nhất)
 * payload: Thông tin gửi lên để cập nhật state(option) || Request body
 * }
 * action creator: function trả về action (object)
 */
export const increment = () =>{
    return {
        type: "counter/increment"
    }
}
export const decrement = () =>{
    return {
        type: "counter/decrement"
    }
}