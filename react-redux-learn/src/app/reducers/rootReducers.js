import { combineReducers } from "redux";
import todoReducer from "./todoReducer";
import counterReducer from "./counterReducer";

const rootReducer = combineReducers({
    counter: counterReducer,
    todos: todoReducer
})

export default rootReducer;