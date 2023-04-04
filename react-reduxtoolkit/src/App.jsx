import { Route, Router, Routes } from 'react-router-dom'
// import './App.css'
import Counter from './components/counter/Counter'
import Layout from './components/layout/Layout'
import TodoList from './components/todo/Todo'
import CourseDetail from './pages/course/CourseDetail'
import ListCourse from './pages/course/ListCourse'
import OnlabCourse from './pages/course/OnlabCourse'
import OnlineCourse from './pages/course/OnlineCourse'
import CourseCreate from './pages/createCourse/CourseCreate'
import CourseEdit from './pages/createCourse/CourseEdit'
import CourseList from './pages/createCourse/CourseList'

function App() {

  return (
    <div className="App">
      <Routes>
        <Route element={<Layout />}>
          <Route path='/khoa-hoc'>
            <Route index element={<ListCourse />} />
            <Route path='online' element={<OnlineCourse />} />
            <Route path='onlab' element={<OnlabCourse />} />
            <Route path=':courseId' element={<CourseDetail />} />
          </Route>
          <Route path='/admin'>
          <Route index element={<CourseList />} />
          <Route path='create' element={<CourseCreate />} />
          <Route path=':adminId' element={<CourseEdit />} />
        </Route>
        </Route>
       
      </Routes>
    </div>
  )
}

export default App
