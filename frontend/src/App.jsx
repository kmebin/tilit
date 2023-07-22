import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import CourseList from './components/course/CourseList';
import Cart from './components/course/Cart';
import SearchBar from './components/common/SearchBar';
import { getCourses, registerCourses } from './apis/course';

function App() {
  const [courses, setCourses] = useState([]);
  const [selectedCourses, setSelectedCourses] = useState([]);
  const [searchInput, setSearchInput] = useState({ keyword: '', category: '' });

  const handleAddToCart = (courseId) => {
    const course = courses.find((course) => course.id === courseId);
    const foundIndex = selectedCourses.findIndex((item) => item.id === courseId);

    if (foundIndex !== -1) {
      const updatedCourses = [...selectedCourses];
      updatedCourses[foundIndex] = { ...updatedCourses[foundIndex] };
      setSelectedCourses(updatedCourses);
    } else {
      setSelectedCourses((prevCourses) => [...prevCourses, { ...course }]);
    }
  };

  const handleRegister = async (courseIds) => {
    await registerCourses(courseIds);
  };

  const handleSearch = async (input) => {
    const courses = await getCourses(input.category, input.keyword);
    setCourses(courses);
  };

  useEffect(() => {
    const fetchData = async () => {
      const courses = await getCourses();
      setCourses(courses);
    };
    fetchData();
  }, []);

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-4'>
        <h1 className='text-center'>
          <span className='text-primary'>tilit</span>
        </h1>
      </Row>
      <Card>
        <Row>
          <Col md={8} className='mt-4 d-flex flex-column align-items-start p-3 pt-0'>
            <Container>
              <Row className='align-items-center'>
                <Col md={7}>
                  <h5>
                    <b>전체 강의</b>
                  </h5>
                </Col>
                <Col md={5}>
                  <SearchBar onClickSearch={handleSearch} />
                </Col>
              </Row>
              <hr />
              <CourseList courses={courses} onClickAddToCart={handleAddToCart} onClickSearch={handleSearch} />
            </Container>
          </Col>
          <Col md={4} className='summary p-4'>
            <Cart courses={selectedCourses} onClickRegister={handleRegister} />
          </Col>
        </Row>
      </Card>
    </Container>
  );
}

export default App;
