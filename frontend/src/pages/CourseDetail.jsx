import { Container, Row } from 'react-bootstrap';
import { deleteCourse, registerCourses } from '../apis/course';
import CourseInfo from '../components/course/CourseInfo';
import { Link, useNavigate } from 'react-router-dom';

const CourseDetail = () => {
  const navigate = useNavigate();

  const handleRegister = async (courseId) => {
    const res = await registerCourses(courseId);
    if (res.status === 401) {
      alert(res.message);
      navigate('/login');
    } else {
      alert(res.message);
    }
  };

  const handleDelete = async (courseId) => {
    const res = await deleteCourse(courseId);
    if (res.status === 200) {
      alert(res.message);
      navigate('/');
    } else if (res.status === 401) {
      alert(res.message);
      navigate('/login');
    } else {
      alert(res.message);
    }
  };

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-4'>
        <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
          <h1 className='text-center'>
            <span className='text-primary'>tilit</span>
          </h1>
        </Link>
      </Row>
      <CourseInfo onClickRegister={handleRegister} onClickDelete={handleDelete} />
    </Container>
  );
};

export default CourseDetail;
