import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import CourseList from './components/course/CourseList';
import { getCourses, registerCourses } from './apis/course';
import Cart from './components/course/Cart';

function App() {
  const [courses, setCourses] = useState([]);
  const [selectedCourses, setSelectedCourses] = useState([]);

  const handleAddToCart = (courseId) => {
    const course = courses.find((course) => course.id === courseId);
    const found = selectedCourses.find((course) => course.id === courseId);
    const updatedItems = found
      ? selectedCourses.map((item) => (item.courseId === courseId ? { ...item, count: item.count + 1 } : item))
      : [...selectedCourses, { ...course, count: 1 }];

    setSelectedCourses(updatedItems);
  };

  const handleRegister = async (courseIds) => {
    await registerCourses(courseIds);
  };

  useEffect(() => {
    const fetchData = async () => {
      const courses = await getCourses();
      setCourses(courses);
    };
    fetchData();
  }, []);

  return (
    <div className='container-fluid'>
      <div className='row justify-content-center m-4'>
        <h1 className='text-center'>tilit</h1>
      </div>
      <div className='card'>
        <div className='row'>
          <div className='col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0'>
            <CourseList courses={courses} onClickAddToCart={handleAddToCart} />
          </div>
          <div className='col-md-4 summary p-4'>
            <Cart courses={selectedCourses} onClickRegister={handleRegister} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
