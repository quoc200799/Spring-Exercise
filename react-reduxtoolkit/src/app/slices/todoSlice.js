import { createSlice } from '@reduxjs/toolkit'

const initialState = [
    { id: 1, title: "Làm bài tập" },
    { id: 2, title: "Đi chơi" },
    { id: 3, title: "Đá bóng" }

];

export const todoReducer = createSlice({
    name: 'todolist', // tên là duy nhất
    initialState,
    reducers: {
        addTodo: (state, action) => { // action type: counter/increment
            state.push(action.payload)
        },
        deleteTodo: (state, action) => { // action type: counter/decrement
            const index = state.findIndex(todo => todo.id === action.payload);
            state.splice(index, 1); // xóa ở vị trí index và xóa 1 phần tử.

        },

    },
})

// Action creators are generated for each case reducer function
export const { addTodo, deleteTodo } = todoReducer.actions

export default todoReducer.reducer