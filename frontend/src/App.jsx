import { useState } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CourseDetail from './pages/CourseDetail';
import CreateCourse from './pages/CreateCourse';
import Login from './pages/Login';
import Main from './pages/Main';
import Signup from './pages/Signup';

const App = () => {
  const [user, setUser] = useState(null);
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main user={user} />} />
        <Route path='/signup' element={<Signup />} />
        <Route path='/login' element={<Login setUser={setUser} />} />
        <Route path='/courses/:courseId' element={<CourseDetail />} />
        <Route path='/courses/create' element={<CreateCourse />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
