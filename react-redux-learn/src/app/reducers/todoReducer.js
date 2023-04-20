// Reducer là pure function: cùng 1 tham số đầu vào
// không có side effect

const initialState = [
    { id: 1, title: "Làm bài tập" },
    { id: 2, title: "Đi chơi" },
    { id: 3, title: "Đá bóng" }

];

const todoReducer = (state = initialState, action) => {
    switch (action.type) {
        case "todo/addTodo": {
            return [...state, action.payload]
        }
        case "todo/deleteTodo": {
            return state.filter(todo => todo.id !== action.payload)
        }
        default: {
            return state;
        }
    }
}

export default todoReducer;