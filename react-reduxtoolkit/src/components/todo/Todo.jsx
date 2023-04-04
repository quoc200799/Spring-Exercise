import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { deleteTodo } from '../../app/slices/todoSlice';

function TodoList() {
    const todoList = useSelector(state => state.todolist);
    const dispactch = useDispatch();
    const handleDelete =(id)=>{
        dispactch(deleteTodo(id));
    }
    console.log(todoList);
    return (
        <div>
            <h2>TodoList App</h2>
            
            {todoList.length > 0 && todoList.map(e => (
                <div key={e.id}>
                    <span>{e.title}</span>
                    <button onClick={() => handleDelete(e.id)}>Delete</button>
                </div>
            ))}
            {todoList.length === 0  && (
                <p>Không có công việc cần tìm nào trong danh sách!!</p>
            )}
        </div>
    )
}

export default TodoList
