import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './pages/Main';
import CourseDetail from './pages/CourseDetail';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/courses/:id' element={<CourseDetail />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
