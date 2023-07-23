import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CourseDetail from './pages/CourseDetail';
import CreateCourse from './pages/CreateCourse';
import Login from './pages/Login';
import Main from './pages/Main';
import Signup from './pages/Signup';
import UpdateCourse from './pages/UpdateCourse';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/signup' element={<Signup />} />
        <Route path='/login' element={<Login />} />
        <Route path='/courses/create' element={<CreateCourse />} />
        <Route path='/courses/:courseId' element={<CourseDetail />} />
        <Route path='/courses/:courseId/update' element={<UpdateCourse />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
