import { createSlice } from '@reduxjs/toolkit'

const initialState = 0 // giá trị ban đầu của state

export const counterReducer = createSlice({
    name: 'counter', // tên là duy nhất
    initialState,
    reducers: {
        increment: (state, action) => { // action type: counter/increment
            return state + 1
        },
        decrement: (state, action) => { // action type: counter/decrement
            return state - 1

        },
        // incrementByAmount: (state, action: PayloadAction<number>) => {
        //     state.value += action.payload
        // },
    },
})

// Action creators are generated for each case reducer function
export const { increment, decrement } = counterReducer.actions

export default counterReducer.reducer