import { Container, Row } from 'react-bootstrap';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { updateCourse } from '../apis/course';
import UpdateCourseForm from '../components/course/UpdateCourseForm';

const UpdateCourse = () => {
  const { courseId } = useParams();
  const navigate = useNavigate();

  const handleUpdate = async (form) => {
    const res = await updateCourse(courseId, form);
    if (res.status === 200) {
      navigate(-1);
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
      <UpdateCourseForm onClickUpdate={handleUpdate} />
    </Container>
  );
};

export default UpdateCourse;
