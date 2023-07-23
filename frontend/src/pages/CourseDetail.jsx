import { useParams } from 'react-router-dom';
import { Container, Row } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import { getCourseDetail, registerCourses } from '../apis/course';
import CourseInfo from '../components/course/CourseInfo';
import { Link } from 'react-router-dom';

const CourseDetail = () => {
  const { courseId } = useParams();
  const [courseDetail, setCourseDetail] = useState({});

  const handleRegister = async (courseId) => {
    await registerCourses(courseId);
  };

  useEffect(() => {
    const fetchData = async () => {
      const res = await getCourseDetail(courseId);
      if (res.status === 200) {
        setCourseDetail(res.data);
      } else {
        alert(res.message);
      }
    };
    fetchData();
  }, [courseId]);

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-4'>
        <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
          <h1 className='text-center'>
            <span className='text-primary'>tilit</span>
          </h1>
        </Link>
      </Row>
      <CourseInfo {...courseDetail} onClickRegister={handleRegister} />
    </Container>
  );
};

export default CourseDetail;
