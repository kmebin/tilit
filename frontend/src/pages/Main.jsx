import { useEffect, useState } from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import CourseList from '../components/course/CourseList';
import Cart from '../components/course/Cart';
import SearchBar from '../components/common/SearchBar';
import { getCourses, registerCourses, deleteCourse } from '../apis/course';
import { Link, useNavigate } from 'react-router-dom';
import { logout } from '../apis/auth';

const Main = () => {
  const navigate = useNavigate();
  const [courses, setCourses] = useState([]);
  const [selectedCourses, setSelectedCourses] = useState([]);
  const nickname = localStorage.getItem('nickname');

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
    const res = await registerCourses(courseIds);
    if (res.status === 401) {
      alert(res.message);
      navigate('/login');
    } else {
      alert(res.message);
      setSelectedCourses([]);
    }
  };

  const handleSearch = async (input) => {
    const res = await getCourses(input.category, input.keyword);
    if (res.status === 200) {
      setCourses(res.data);
    } else {
      alert(res.message);
    }
  };

  const handleDelete = async (courseId) => {
    const res = await deleteCourse(courseId);
    if (res.status === 200) {
      alert(res.message);
      const courses = await getCourses();
      setCourses(courses.data);
    } else {
      alert(res.message);
    }
  };

  const handleLogout = async () => {
    const res = await logout();
    if (res.status === 200) {
      localStorage.removeItem('id');
      localStorage.removeItem('nickname');
      alert(res.message);
      navigate('/');
    } else {
      alert(res.message);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const res = await getCourses();
      if (res.status === 200) {
        setCourses(res.data);
      } else {
        alert(res.message);
      }
    };
    fetchData();
  }, []);

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-4'>
        <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
          <h1 className='text-center'>
            <span className='text-primary'>tilit</span>
          </h1>
        </Link>
      </Row>
      {nickname ? (
        <div className='d-flex justify-content-end align-items-center m-3' style={{ lineHeight: 1.5 }}>
          <div className='d-flex align-items-center mx-3'>
            <h6 className='mb-0'>
              <span className='text-primary'>{nickname}</span> 님 환영합니다!
            </h6>
          </div>
          <div className='d-flex justify-content-end'>
            <Link to={`/courses/create`} style={{ textDecoration: 'none', color: 'inherit' }}>
              <Button className='me-2'>강의 생성</Button>
            </Link>
            <Button variant='outline-danger' onClick={handleLogout}>
              로그아웃
            </Button>
          </div>
        </div>
      ) : (
        <div className='d-flex justify-content-end m-3'>
          <Link to={`/login`} style={{ textDecoration: 'none', color: 'inherit' }}>
            <Button variant='outline-primary' className='me-2'>
              로그인
            </Button>
          </Link>
          <Link to={`/signup`} style={{ textDecoration: 'none', color: 'inherit' }}>
            <Button variant='outline-primary'>회원가입</Button>
          </Link>
        </div>
      )}
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
              <CourseList
                courses={courses}
                onClickAddToCart={handleAddToCart}
                onClickSearch={handleSearch}
                onClickDelete={handleDelete}
              />
            </Container>
          </Col>
          <Col md={4} className='summary p-4'>
            <Cart courses={selectedCourses} onClickRegister={handleRegister} />
          </Col>
        </Row>
      </Card>
    </Container>
  );
};

export default Main;
