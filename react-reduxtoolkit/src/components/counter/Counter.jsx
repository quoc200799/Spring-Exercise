import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { decrement, increment } from '../../app/slices/counterSlices';

function Counter() {
    const counter = useSelector(state => state.counter)
    const dispactch = useDispatch();
    const handleDecrement = () => { dispactch(decrement())}
    const handleIncrement = () => { dispactch(increment())}
    console.log(counter);

    return (
        <div>
            <h1>Counter: {counter}</h1>
            <button onClick={handleIncrement}>+</button>
            <button onClick={handleDecrement}>-</button>

        </div>
    )
}

export default Counter
