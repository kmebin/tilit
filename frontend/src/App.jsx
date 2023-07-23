import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './pages/Main';
import CourseDetail from './pages/CourseDetail';
import Signup from './pages/Signup';
import Login from './pages/Login';
import { useState } from 'react';

const App = () => {
  const [user, setUser] = useState(null);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main user={user} />} />
        <Route path='/signup' element={<Signup />} />
        <Route path='/login' element={<Login setUser={setUser} />} />
        <Route path='/courses/:courseId' element={<CourseDetail />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
