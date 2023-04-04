import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { useGetCourseByIdQuery, getAllUsers, getAllCategories } from '../course/courseService';
import Select from 'react-select';


function CourseEdit() {
    const { adminId } = useParams();
    const [users, setUsers] = useState([]);
    const [user, setUser] = useState({})

    useEffect(() => {
        getAllUser();
    }, [])
    const getAllUser = () => {
        getAllUsers()
            .then((res) => setUsers(res.data))
    };
    const { id, data, isLoading } = useGetCourseByIdQuery(adminId);

    const handleSelectUsers = (selectedOption) => {
        setUser(selectedOption)
    }
    return (
        <div className="course-list mt-4 mb-4">
            <div className="container">
                <div className="mb-4 d-flex justify-content-between">
                    <div>
                        <button className="btn-custom btn-update-course">
                            <span><i className="fa-solid fa-plus"></i></span>
                            Cập nhật
                        </button>
                        <Link to="" className="btn-custom btn-refresh">
                            <span><i className="fa-solid fa-angle-left"></i></span>
                            Quay lại
                        </Link>
                    </div>
                    <div>
                        <button className="btn-custom btn-delete-course bg-danger">
                            <span><i className="fa-solid fa-trash-can"></i></span>
                            Xóa
                        </button>
                    </div>
                </div>

                <div className="course-list-inner p-2">
                    <div className="row">
                        <div className="col-md-8">
                            <div className="mb-3">
                                <label htmlFor="course-name" className="form-label fw-bold">Tên khóa học</label>
                                <input type="text" className="form-control" id="course-name" value={data?.name} />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-description" className="form-label fw-bold">Mô tả</label>
                                <textarea className="form-control" id="course-description" rows="10"></textarea>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="mb-3">
                                <label htmlFor="course-type" className="form-label fw-bold">Hình thức học</label>
                                <select className="form-control" id="course-type">
                                    <option hidden>- Chọn hình thức học</option>
                                    <option value="online">Online</option>
                                    <option value="onlab">Onlab</option>
                                </select>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-topic" className="form-label fw-bold">Chủ đề</label>
                                <Select

                                    className="form-control"
                                //     onChange={handleSelectUsers}
                                //  defaultValue={data?.user}
                                //     options={users}
                                // name="name"
                                />
                                {/* <select className="form-control" id="course-topic" multiple="multiple">
                                    <option value="1">Backend</option>
                                    <option value="2">Frontend</option>
                                    <option value="3">Mobile</option>
                                    <option value="4">Lập trình web</option>
                                    <option value="5">Database</option>
                                    <option value="6">Devops</option>
                                </select> */}
                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-supporter" className="form-label fw-bold">Tư vấn viên</label>
                                <Select
                                    // className="form-control"
                                    options={users}
                                    onChange={handleSelectUsers}
                                />

                                {/* <select className="form-control" id="course-supporter">
                                    <option hidden>- Chọn tư vấn viên</option>
                                    <option value="1">Nguyễn Văn A</option>
                                    <option value="2">Trần Văn B</option>
                                    <option value="3">Ngô Thị C</option>
                                </select> */}
                            </div>

                            <div className="mb-3">
                                <label className="form-label fw-bold">Thumnail</label>
                                <div className="course-logo-preview mb-3 rounded">
                                    <img id="course-logo-preview" className="rounded" />
                                </div>

                                <label htmlFor="course-logo-input" className="btn btn-warning">Đổi ảnh</label>
                                <input type="file" id="course-logo-input" className="d-none" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CourseEdit
