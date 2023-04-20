export const addTodo = (data) => {
    return {
        type: "todo/addTodo",
        payload: data
    }
}
export const deleteTodo = (id) => {
    return {
        type: "todo/deleteTodo",
        payload: id
    }
}