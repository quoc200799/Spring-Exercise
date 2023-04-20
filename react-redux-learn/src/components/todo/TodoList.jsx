import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
// import { deleteTodo } from '../../app/actions/todoActions';
import { getAllTodos, createTodo, updateTodo, deleteTodo } from './todoService';


function TodoList() {
    const [todos, setTodos] = useState([]);
    const [checked, setChecked] = useState(false);
    const [data, setData] = useState('');
    const [title, setTitle] = useState('');

    useEffect(() => {
        getTodos();
        // getAllCategory();
    }, [])
    const getTodos = () => {
        getAllTodos()
            .then((res) => setTodos(res.data))
            .catch(e => console.log(e))
    };
    const handleDelete = (id) => {
        deleteTodo(id)
            .then((res) => getTodos())
            .catch(e => console.log(e))
    };
    const handleEdit = (id, title) => {
        const object = new Object;
        object.checked = checked;
        let person = prompt("Please enter", title);
        if (person != null) {
            object.title = person;
            updateTodo(id, object)
                .then((res) => getTodos())
                .catch(e => console.log(e))
        }
    }
    // console.log(checked);
    const handleCheckbox = (e) => {
        setChecked(e.target.checked);
    }
    const handleAdd = () =>{
        const object = new Object;
        object.title = title;
        createTodo(object)
        .then((res) =>{ 
            getTodos()
            setTitle("")
        alert("them moi thanh cong")
        })
        .catch(e => console.log(e))
    }
    return (
        <div>
            <h2>TodoList App</h2>
            <input
                type="text"
                id="title"
                name="title"
                onChange={(e) => {
                    setTitle(e.target.value)
                }}
                value={title}
            />
            <button onClick={handleAdd}>Add</button>
            {todos.length > 0 && todos.map(ele => (
                <div key={ele.id}>
                    <input
                        type="checkbox"
                        onChange={(e) => {
                            handleCheckbox(e)
                        }}
                    />
                    <span>{ele?.title}</span>
                    <button onClick={() => handleEdit(ele.id, ele?.title)}>Sửa</button>
                    <button onClick={() => handleDelete(ele?.id)}>Delete</button>
                </div>
            ))}
            {todos.length === 0 && (
                <p>Không có công việc cần tìm nào trong danh sách!!</p>
            )}
        </div>
    )
}

export default TodoList
