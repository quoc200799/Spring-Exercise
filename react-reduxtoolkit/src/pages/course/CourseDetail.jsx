import React from 'react'
import { Link, useParams } from 'react-router-dom';
import { useGetCourseByIdQuery } from './courseService';

function CourseDetail() {
    const { courseId } = useParams();
   
    const { id, data, isLoading } = useGetCourseByIdQuery(courseId);
  return (
    <div className="course-container mt-5">
        <div className="container">
            <div className="mb-4">
                <nav  aria-label="breadcrumb">
                    <ol className="breadcrumb">
                        <li className="breadcrumb-item">
                            <Link to="khoa-hoc">Khóa học</Link>
                        </li>
                        <li className="breadcrumb-item active" aria-current="page">
                            {data?.name}
                        </li>
                    </ol>
                </nav>
            </div>
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <div className="main p-4 shadow-sm">
                        <h2 className="course-title fs-5">
                        {data?.name}
                        </h2>

                        <hr />

                        <div className="supporter d-flex align-items-center">
                            <div className="supporter-image">
                                <img src={data?.user?.avatar}
                                    alt="tư vấn viên" className="rounded-circle w-50 h-50" />
                            </div>
                            <div className="supporter-info">
                                <p>
                                    <b>Tư vấn viên :</b>
                                    {data?.user?.name}
                                </p>
                                <p>
                                    <b>Email :</b>
                                    {data?.user?.email}
                                </p>
                                <p>
                                    <b>Số điện thoại :</b>
                                    {data?.user?.phone}
                                </p>
                            </div>
                        </div>

                        <hr />

                        <div className="course-description">
                            <p>
                               {data?.description}
                            </p>
                        </div>
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="p-4 shadow-sm">
                        <div className="course-image mb-4">
                            <img src={data?.thumbnail} />
                        </div>
                        <p>
                            Học phí :
                            <span className="fw-bold course-price">
                                {data?.price.toLocaleString()} VND
                            </span>
                        </p>
                        <p>
                            Hình thức học :
                            <span className="fw-bold course-type">{data?.type}</span>
                        </p>
                        <button className="btn btn-success">
                            Thêm vào giỏ hàng
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default CourseDetail
