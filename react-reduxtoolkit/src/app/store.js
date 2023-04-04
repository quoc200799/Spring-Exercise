import { configureStore } from "@reduxjs/toolkit";
import  {courseApi}  from "../pages/course/courseService";
import  counterReducer from "./slices/counterSlices";
import  todoReducer  from "./slices/todoSlice";

const store = configureStore({
    reducer:{
        counter: counterReducer,
        todolist: todoReducer,
        [courseApi.reducerPath]: courseApi.reducer
    },

    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(courseApi.middleware),
})

export default store;


