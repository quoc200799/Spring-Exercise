import React, { useEffect, useState } from 'react'
import { useForm, Controller } from 'react-hook-form';
import { Link } from 'react-router-dom'
import { Navigate } from 'react-router-dom';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import { getAllUsers, getAllCategories } from '../course/courseService';
import Select from 'react-select';



function CourseCreate() {

    const [users, setUsers] = useState([]);
    const [categories, setCategories] = useState([]);

    const [user, setUser] = useState({})
    // const { control, register, handleSubmit, errors, onCreateCourse } =
    // useCreate();
    useEffect(() => {
        getAllUser();
        getAllCategory();
    }, [])
    const getAllUser = () => {
        getAllUsers()
            .then((res) => setUsers(res.data))
            .catch(e => console.log(e))
    };
    const getAllCategory = () => {
        getAllCategories()
            .then((res) => setCategories(res.data))
            .catch(e => console.log(e))
    };

    // const optionUsers = user

    // console.log(register);
    const schema = yup.object({
        name: yup.string().required("Tên khóa học không được để trống"),
        type: yup.string().required("Hình thức học không được để trống"),
        user: yup.string().required("Người tư vấn không được để trống"),
        // user: yup.object().required("không được để trống người tư vấn")

    })
    const { control, register, handleSubmit,
        formState: { errors } } = useForm({
            resolver: yupResolver(schema),
            defaultValues: {
                select: {}
            }
        });
    const onSubmit = (data) => {
        console.log(data);
    };
    const handleSelectUsers = (selectedOption) => {
        setUser(selectedOption)
    }
    const optionUsers = users?.map(function (user) {
        return {
            value: user?.id,
            label: user?.name
        };
    })
    return (
        <div className="course-list mt-4 mb-4">
            <form className="container" onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                    <button className="btn-custom btn-create-course" type="submit">
                        <span><i className="fa-solid fa-plus"></i></span>
                        Tạo
                    </button>
                    <Link to="create" className="mx-2 btn-custom btn-refresh">
                        <span><i className="fa-solid fa-angle-left"></i></span>
                        Quay lại
                    </Link>
                </div>

                <div className="course-list-inner p-2">
                    <div className="row">
                        <div className="col-md-8">
                            <div className="mb-3">
                                <label htmlFor="course-name" className="form-label fw-bold">Tên khóa học</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="course-name"
                                    {...register("name")}
                                />
                                <p className="text-danger">{errors.name?.message}</p>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-description" className="form-label fw-bold">Mô tả</label>
                                <textarea className="form-control" id="course-description" rows="10"></textarea>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="mb-3">
                                <label htmlFor="course-type" className="form-label fw-bold">Hình thức học</label>
                                <select className="form-control" id="course-type"  {...register("type")}>
                                    <option value="" hidden>- Chọn hình thức học</option>
                                    <option value="online">Online</option>
                                    <option value="onlab">Onlab</option>
                                </select>
                                <p className="text-danger">{errors.type?.message}</p>

                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-topic" className="form-label fw-bold">Chủ đề</label>
                                <select className="form-control" id="course-topic" multiple="multiple">
                                    <option value="1">Backend</option>
                                    <option value="2">Frontend</option>
                                    <option value="3">Mobile</option>
                                    <option value="4">Lập trình web</option>
                                    <option value="5">Database</option>
                                    <option value="6">Devops</option>
                                </select>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="course-supporter" className="form-label fw-bold">Tư vấn viên</label>
                                <Controller
                                    name="userId"
                                    control={control}
                                    defaultValue={optionUsers[0]?.value}
                                    render={({field}) =>
                                        <Select
                                            {...field}
                                            placeholder="-- Chọn nhân viên tư vấn --"
                                            options={optionUsers}                            
                                            value={optionUsers.find(c => c?.value === field?.value)}
                                            onChange={(val) => field.onChange(val.value)}                                   
                                        />
                                    }
                                />
                                {/* <select className="form-control" id="course-supporter" {...register("user")}>
                                    <option value="" hidden>- Chọn người tư vấn</option>
                                    {users?.map((item) =>
                                        <option value={JSON.stringify(item)}>{item?.name}</option>
                                    )}
                                </select>
                                <p className="text-danger">{errors.user?.message}</p> */}
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    )
}

export default CourseCreate
