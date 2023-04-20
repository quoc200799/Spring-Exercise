import axios from "axios"

const API_ENPOINT = "http://localhost:8080/api/todos";

export const getAllTodos = () => {
    return axios.get(API_ENPOINT)
}

export const updateTodo = (id, data) => {
    return axios.put(API_ENPOINT + "/" + id, data)
}

export const createTodo = (data) => {
    return axios.post(API_ENPOINT,data)
}
export const deleteTodo = (id) => {
    return axios.delete(API_ENPOINT + "/" + id)
}