import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Counter from './components/counter/Counter'
import TodoList from './components/todo/TodoList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <Counter />
      <TodoList />
    </div>
  )
}

export default App
