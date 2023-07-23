import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './pages/Main';
import CourseDetail from './pages/CourseDetail';
import Signup from './pages/Signup';
import Login from './pages/Login';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/signup' element={<Signup />} />
        <Route path='/login' element={<Login />} />
        <Route path='/courses/:courseId' element={<CourseDetail />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
