import { Container, Row } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { createCourse } from '../apis/course';
import CreateCourseForm from '../components/course/CreateCourseForm';

const CreateCourse = () => {
  const navigate = useNavigate();

  const handleCreate = async (form) => {
    const res = await createCourse(form);
    if (res.status === 201) {
      navigate('/');
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
      <CreateCourseForm onClickCreate={handleCreate} />
    </Container>
  );
};

export default CreateCourse;
