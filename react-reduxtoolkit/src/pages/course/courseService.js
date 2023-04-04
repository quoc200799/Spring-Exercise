import axios from "axios";
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

const API_ENPOINT = "http://localhost:8080/v1/api/";

// Define a service using a base URL and expected endpoints
export const courseApi = createApi({
    reducerPath: 'courseApi',
    baseQuery: fetchBaseQuery({ baseUrl: API_ENPOINT }),
    tagTypes: ['Courses'],
    endpoints: (builder) => ({
        getAllCourse: builder.query({
            query: () => `courses`,
            providesTags: ['Post'],
        }),
        getCourseById: builder.query({
            query: (id) => `courses/${id}`
        }),
        createCourse: builder.query({
            query: (data) => ({
                url: "courses",
                method: "POST",
                body: data
            })
        }),
        updateCourse: builder.query({
            query: ({ id, ...data }) => ({
                url: "course",
                method: "PUT",
                body: data
            })
        }),
        deleteCourse: builder.query({
            query: (id) => ({
                url: `course/${id}`,
                method: "DELETE",
            }),
            invalidatesTags: ["Post"]
        }),

    }),
})

export const getAllUsers = () => {
    return axios.get(API_ENPOINT + "users")
}
export const getAllCategories = () => {
    return axios.get(API_ENPOINT + "categories")
}
export const getAllCourseType = (search) => {
    // const form = new FormData();
    // form.append('name', name);
    // form.append('type', type);
    // form.append('categories', categories);

    return axios.get(API_ENPOINT + "courses/abc?type=" + search)
}
export const {
    useGetAllCourseQuery,
    useGetCourseByIdQuery,
    useCreateCourseQuery,
    useUpdateCourseQuery,
    useDeleteCourseQuery } = courseApi

