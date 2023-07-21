import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import CourseList from './components/CourseList';
import { getCourses } from './apis/course';

function App() {
  const [courses, setCourses] = useState([]);
  const [items, setItems] = useState([]);

  const handleAddToCart = (courseId) => {
    const course = courses.find((course) => course.id === courseId);
    const found = items.find((item) => item.courseId === courseId);
    const updatedItems = found
      ? items.map((item) => (item.courseId === courseId ? { ...item, count: item.count + 1 } : item))
      : [...items, { ...course, count: 1 }];

    setItems(updatedItems);
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
        </div>
      </div>
    </div>
  );
}

export default App;
