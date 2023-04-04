import React from 'react'
import { Link } from 'react-router-dom'
import { useGetAllCourseQuery } from '../course/courseService'

function CourseList() {
    const { data, isLoading, isError, error } = useGetAllCourseQuery();
    if (isLoading) {
        return <h2> Loading... </h2>
    } else if (isError) {
        return <h2> Error: {error}</h2>
    }
    return (
        <div className="course-list mt-4 mb-4">
            <div className="container">
                <div className="mb-4">
                    <Link to="create" className="btn-custom btn-create-course">
                        <span><i className="fa-solid fa-plus pr-2"></i></span>
                        Tạo khóa học
                    </Link>
                    <Link to="" className="btn-custom btn-refresh mx-4">
                        <span><i className="fa-solid fa-arrow-rotate-right"></i></span>
                        Refresh
                    </Link>
                </div>

                <div className="course-list-inner p-2">
                    <table className="table course-table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên khóa học</th>
                                <th>Hình thức</th>
                                <th>Chủ đề</th>
                            </tr>
                        </thead>
                        <tbody>
                        {data.map((ele,index) => 
                            <tr key={ele?.id}>
                                <td >{index+1}</td>
                                <td>
                                    <Link to={`${ele?.id}`}>{ele?.name}</Link>
                                </td>
                                {ele?.type === "online" ?  <td className="text-info">{ele?.type}</td> :
                                <td className="text-danger">{ele?.type}</td>
                                }
                               
                                <td>{ele?.categories.map(item =>
                                     " - " +item?.name 
                                )}</td>
                            </tr>
                        )}
                          
                        
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}


export default CourseList
